package kr.co.blueprint.application.service;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.adapter.port.in.web.dto.user.UserResponseDto;
import kr.co.blueprint.domain.port.in.redis.user.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRedisRepository userRepository;

    public void setUser(UserRequestDto dto) {
        userRepository.set(dto);
    }

    public UserResponseDto getUser(String email) {
        return userRepository.get(email);
    }

    public Page<UserResponseDto> getAllUserListPage(Pageable pageable) {
        return userRepository.getAllPage(pageable);
    }

    public void deleteUser(String email) {
        userRepository.delete(email);
    }
}