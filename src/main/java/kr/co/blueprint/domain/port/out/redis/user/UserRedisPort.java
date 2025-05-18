package kr.co.blueprint.domain.port.out.redis.user;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.adapter.port.in.web.dto.user.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRedisPort {
    void set(UserRequestDto dto);
    UserResponseDto get(String email);
    Page<UserResponseDto> getAllPage(Pageable pageable);
    void delete(String email);
}