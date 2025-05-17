package kr.co.blueprint.domain.port.in.mongodb.message;

import kr.co.blueprint.domain.entity.message.MessageDocument;

public interface MessageMongodbRepository {
    void save(MessageDocument messageDocument);
}
