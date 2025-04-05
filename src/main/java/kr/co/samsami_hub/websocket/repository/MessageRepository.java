package kr.co.samsami_hub.websocket.repository;

import kr.co.samsami_hub.websocket.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, Long> {
}
