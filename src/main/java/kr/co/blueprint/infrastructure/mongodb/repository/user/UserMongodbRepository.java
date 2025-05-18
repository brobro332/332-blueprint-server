package kr.co.blueprint.infrastructure.mongodb.repository.user;

import kr.co.blueprint.domain.entity.user.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongodbRepository extends MongoRepository<UserDocument, String> {
}
