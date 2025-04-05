package kr.co.samsami_hub.redis.service;

import kr.co.samsami_hub.redis.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RedisTemplate<String, Object> template;
    private static final String USER_KEY_PREFIX = "user:";

    public void setUser(UserDto dto) {
        template.opsForValue().set(USER_KEY_PREFIX + dto.getEmail(), dto);
    }

    public UserDto getUser(UserDto dto) {
        return (UserDto) template.opsForValue().get(USER_KEY_PREFIX + dto.getEmail());
    }

    public void deleteUser(UserDto dto) {
        template.delete(USER_KEY_PREFIX + dto.getEmail());
    }
}
