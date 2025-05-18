package kr.co.blueprint.adapter.port.in.kafka;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.common.mapper.UserMapper;
import kr.co.blueprint.domain.port.out.mongodb.user.UserMongodbPort;
import kr.co.blueprint.domain.port.out.redis.user.UserRedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUserEventConsumer {
    private final UserRedisPort redisRepository;
    private final UserMongodbPort userMongodbPort;

    @KafkaListener(topics = "user.set", groupId = "user-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleSetUser(UserRequestDto dto) {
        redisRepository.set(dto);
        userMongodbPort.save(UserMapper.toDocument(dto));
    }

    @KafkaListener(topics = "user.delete", groupId = "user-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleDeleteUser(UserRequestDto dto) {
        userMongodbPort.delete(UserMapper.toDocument(dto));
        redisRepository.delete(dto.getEmail());
    }
}
