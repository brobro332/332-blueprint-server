package kr.co.samsami_hub.websocket;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "messages")
public class Message {
    @Id
    private String messageId;
    private Long senderId;
    private Long roomId;
    private String content;
    private String senderName;
    private MessageType type;
    private LocalDateTime sendAt;

    @Builder
    public Message(Long senderId, Long roomId, String content, String senderName, MessageType type, LocalDateTime sendAt) {
        this.senderId = senderId;
        this.roomId = roomId;
        this.content = content;
        this.senderName = senderName;
        this.type = type;
        this.sendAt = sendAt;
    }
}
