package kr.co.blueprint.infrastructure.mongodb.repository.message;

import kr.co.blueprint.domain.entity.message.MessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageMongodbRepository extends MongoRepository<MessageDocument, String> {
}
