package kr.co.blueprint.adapter.port.in.kafka;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.domain.port.out.redis.user.UserRedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUserEventConsumer {
    private final UserRedisPort redisRepository;

    @KafkaListener(topics = "user.set", groupId = "user-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleSetUser(UserRequestDto dto) {
        redisRepository.set(dto);
    }

    @KafkaListener(topics = "user.delete", groupId = "user-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleDeleteUser(String email) {
        redisRepository.delete(email);
    }
}
