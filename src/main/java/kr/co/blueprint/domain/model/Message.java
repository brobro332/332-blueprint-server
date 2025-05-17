package kr.co.blueprint.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Message {
    private final String messageId;
    private final Long senderId;
    private final Long roomId;
    private final String content;
    private final String senderName;
    private final MessageType type;
    private final LocalDateTime sendAt;

    @Builder
    public Message(String messageId, Long senderId, Long roomId, String content, String senderName, MessageType type, LocalDateTime sendAt) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.roomId = roomId;
        this.content = content;
        this.senderName = senderName;
        this.type = type;
        this.sendAt = sendAt;
    }
}
