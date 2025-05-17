package kr.co.blueprint.domain.port.in.redis.user;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.adapter.port.in.web.dto.user.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRedisRepository {
    void set(UserRequestDto dto);
    UserResponseDto get(String email);
    Page<UserResponseDto> getAllPage(Pageable pageable);
    void delete(String email);
}