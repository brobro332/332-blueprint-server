package kr.co.blueprint.domain.port.out.mongodb.message;

import kr.co.blueprint.domain.entity.message.MessageDocument;

public interface MessageMongodbPort {
    void save(MessageDocument messageDocument);
}
