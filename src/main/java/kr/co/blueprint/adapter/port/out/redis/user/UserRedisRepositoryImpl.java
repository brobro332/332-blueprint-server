package kr.co.blueprint.adapter.port.out.redis.user;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.adapter.port.in.web.dto.user.UserResponseDto;
import kr.co.blueprint.common.mapper.UserMapper;
import kr.co.blueprint.domain.port.in.redis.user.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRedisRepositoryImpl implements UserRedisRepository {
    private final RedisTemplate<String, Object> template;
    private static final String USER_KEY_PREFIX = "user:";
    private static final String USER_KEYS_SET = "user:keys";

    @Override
    public void set(UserRequestDto dto) {
        String key = USER_KEY_PREFIX + dto.getEmail();

        template.opsForHash().put(key, "email", dto.getEmail());
        template.opsForHash().put(key, "name", dto.getName());
        template.opsForHash().put(key, "description", dto.getDescription());
        template.opsForHash().put(key, "phoneNumber", dto.getPhoneNumber());
        template.opsForHash().put(key, "service", dto.getService());

        template.opsForSet().add(USER_KEYS_SET, key);
    }

    @Override
    public UserResponseDto get(String email) {
        String key = USER_KEY_PREFIX + email;
        Map<Object, Object> map = template.opsForHash().entries(key);

        if (map.isEmpty()) return null;

        return UserMapper.mapToDto(map);
    }

    @Override
    public Page<UserResponseDto> getAllPage(Pageable pageable) {
        // 1. 모든 키 목록 가져오기
        Set<Object> keySet = template.opsForSet().members(USER_KEYS_SET);

        // 2. 가져온 키 목록이 비었다면 빈 페이지 반환
        if (keySet == null || keySet.isEmpty()) return Page.empty(pageable);

        // 3. 정렬 처리
        List<String> allKeys = keySet.stream()
            .map(Object::toString)
            .map(key -> Map.entry(key, Objects.requireNonNull(template.opsForHash().get(key, "service"))))
            .sorted(
                Comparator.comparing(
                    entry -> (String) entry.getValue(),
                    Comparator.nullsLast(String::compareTo)
                )
            )
            .map(Map.Entry::getKey)
            .toList();

        // 4. 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), allKeys.size());
        List<String> pagedKeys = allKeys.subList(start, end);

        // 5. 반환 사용자 목록 생성
        List<UserResponseDto> userList = pagedKeys.stream()
            .map(key -> template.opsForHash().entries(key))
            .map(UserMapper::mapToDto)
            .toList();

        // 6. 반환
        return new PageImpl<>(userList, pageable, allKeys.size());
    }

    @Override
    public void delete(String email) {
        String key = USER_KEY_PREFIX + email;

        template.delete(key);
        template.opsForSet().remove(USER_KEYS_SET, key);
    }
}
