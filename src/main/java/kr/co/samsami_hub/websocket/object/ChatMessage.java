package kr.co.samsami_hub.websocket.object;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ChatMessage {
    public enum MessageType {
        JOIN, CHAT, LEAVE
    }

    private String messageId;
    private MessageType type;
    private String content;
    private String senderId;
    private String roomId;
    private LocalDateTime timestamp;
    private boolean isRead;
}
