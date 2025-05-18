package kr.co.blueprint.adapter.port.out.kafka;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.common.mapper.UserMapper;
import kr.co.blueprint.domain.port.out.kafka.user.UserEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUserEventProducer implements UserEventPort {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publishSetUserEvent(@Payload UserRequestDto dto) {
        kafkaTemplate.send("user.set", UserMapper.toJson(dto));
    }

    @Override
    public void publishDeleteUserEvent(String email) {
        kafkaTemplate.send("user.delete", email);
    }
}
