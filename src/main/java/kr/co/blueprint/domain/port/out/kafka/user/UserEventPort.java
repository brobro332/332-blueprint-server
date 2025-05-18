package kr.co.blueprint.domain.port.out.kafka.user;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;

public interface UserEventPort {
    void publishSetUserEvent(UserRequestDto dto);
    void publishDeleteUserEvent(String email);
}
