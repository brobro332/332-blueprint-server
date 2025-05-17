package kr.co.blueprint.infrastructure.mongodb.entity;

import kr.co.blueprint.domain.model.Message;
import kr.co.blueprint.domain.model.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDocument {

    @Id
    private String messageId;
    private Long senderId;
    private Long roomId;
    private String content;
    private String senderName;
    private MessageType type;
    private LocalDateTime sendAt;

    public static MessageDocument fromDomain(Message message) {
        return MessageDocument.builder()
                .messageId(message.getMessageId())
                .senderId(message.getSenderId())
                .roomId(message.getRoomId())
                .content(message.getContent())
                .senderName(message.getSenderName())
                .type(message.getType())
                .sendAt(message.getSendAt())
                .build();
    }

    public Message toDomain() {
        return Message.builder()
                .messageId(messageId)
                .senderId(senderId)
                .roomId(roomId)
                .content(content)
                .senderName(senderName)
                .type(type)
                .sendAt(sendAt)
                .build();
    }
}
