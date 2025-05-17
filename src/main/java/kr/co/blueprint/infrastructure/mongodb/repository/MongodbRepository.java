package kr.co.blueprint.infrastructure.mongodb.repository;

import kr.co.blueprint.domain.entity.message.MessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongodbRepository extends MongoRepository<MessageDocument, String> {
}
