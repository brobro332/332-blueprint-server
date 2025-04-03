package kr.co.samsami_hub.websocket.object;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class WebSocketMessage {
    public enum MessageType {
        ENTER, CHAT, LEAVE
    }

    private MessageType type;
    private String content;
    private String senderId;
    private String senderName;
    private String roomId;
    private LocalDateTime timestamp;
}
