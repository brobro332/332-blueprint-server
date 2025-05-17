package kr.co.blueprint.adapter.port.out.mongodb.message;

import kr.co.blueprint.domain.port.in.mongodb.message.MessageMongodbRepository;
import kr.co.blueprint.domain.entity.message.MessageDocument;
import kr.co.blueprint.infrastructure.mongodb.repository.MongodbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageMongodbRepositoryImpl implements MessageMongodbRepository {
    private final MongodbRepository mongodbRepository;

    @Override
    public void save(MessageDocument messageDocument) {
        mongodbRepository.save(messageDocument);
    }
}
