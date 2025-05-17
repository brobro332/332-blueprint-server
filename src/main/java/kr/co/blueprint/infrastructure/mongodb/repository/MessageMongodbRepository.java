package kr.co.blueprint.infrastructure.mongodb.repository;

import kr.co.blueprint.infrastructure.mongodb.entity.MessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageMongodbRepository extends MongoRepository<MessageDocument, String> {
}
