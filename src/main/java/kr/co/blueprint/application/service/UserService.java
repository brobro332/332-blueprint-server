package kr.co.blueprint.application.service;

import kr.co.blueprint.adapter.port.in.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RedisTemplate<String, Object> template;
    private static final String USER_KEY_PREFIX = "user:";

    public void setUser(UserDto dto) {
        String key = "user:" + dto.getEmail();

        template.opsForHash().put(key, "email", dto.getEmail());
        template.opsForHash().put(key, "name", dto.getName());
        template.opsForHash().put(key, "description", dto.getDescription());
        template.opsForHash().put(key, "phoneNumber", dto.getPhoneNumber());
        template.opsForHash().put(key, "service", dto.getService());

        template.opsForSet().add("user:keys", key);
    }

    public UserDto getUser(String email) {
        String key = USER_KEY_PREFIX + email;
        Map<Object, Object> map = template.opsForHash().entries(key);

        if (map.isEmpty()) return null;

        UserDto dto = new UserDto();
        dto.setEmail((String) map.get("email"));
        dto.setName((String) map.get("name"));
        dto.setPhoneNumber((String) map.get("phoneNumber"));
        dto.setService((String) map.get("service"));
        dto.setDescription((String) map.get("description"));

        return dto;
    }

    public Page<UserDto> getAllUserListPage(Pageable pageable) {
        // 1. 모든 키 목록 가져오기
        Set<Object> keySet = template.opsForSet().members("user:keys");

        // 2. 가져온 키 목록이 비었다면 빈 페이지 반환
        if (keySet == null || keySet.isEmpty()) return Page.empty(pageable);

        // 3. 정렬 처리
        List<String> allKeys = keySet.stream()
            .map(Object::toString)
            .map(key -> Map.entry(key, template.opsForHash().get(key, "service")))
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
        List<UserDto> userList = pagedKeys.stream()
            .map(key -> template.opsForHash().entries(key))
            .map(map -> {
                UserDto dto = new UserDto();
                dto.setEmail((String) map.get("email"));
                dto.setName((String) map.get("name"));
                dto.setDescription((String) map.get("description"));
                dto.setPhoneNumber((String) map.get("phoneNumber"));
                dto.setService((String) map.get("service"));
                return dto;
            })
            .toList();

        // 6. 반환
        return new PageImpl<>(userList, pageable, allKeys.size());
    }

    public void deleteUser(String email) {
        String key = USER_KEY_PREFIX + email;

        template.delete(key);
        template.opsForSet().remove("user:keys", key);
    }
}
