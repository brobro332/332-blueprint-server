package kr.co.blueprint.adapter.port.out.mongodb.message;

import kr.co.blueprint.domain.entity.message.MessageDocument;
import kr.co.blueprint.domain.port.out.mongodb.message.MessageMongodbPort;
import kr.co.blueprint.infrastructure.mongodb.repository.message.MessageMongodbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageMongodbPortImpl implements MessageMongodbPort {
    private final MessageMongodbRepository repository;
    @Override
    public void save(MessageDocument messageDocument) {
        repository.save(messageDocument);
    }
}
