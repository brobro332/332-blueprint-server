package kr.co.blueprint.application.service;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.adapter.port.in.web.dto.user.UserResponseDto;
import kr.co.blueprint.domain.port.out.redis.user.UserRedisPort;
import kr.co.blueprint.domain.port.out.kafka.user.UserEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRedisPort repository;
    private final UserEventPort port;

    public void setUser(UserRequestDto dto) {
        port.publishSetUserEvent(dto);
    }

    public UserResponseDto getUser(String email) {
        return repository.get(email);
    }

    public Page<UserResponseDto> getAllUserListPage(Pageable pageable) {
        return repository.getAllPage(pageable);
    }

    public void deleteUser(String email) {
        port.publishDeleteUserEvent(email);
    }
}