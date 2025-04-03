package kr.co.samsami_hub.websocket.service;

import kr.co.samsami_hub.websocket.object.WebSocketMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WebSocketService {
    public WebSocketMessage createSystemMessage(WebSocketMessage message) {
        WebSocketMessage.MessageType type = message.getType();

        return WebSocketMessage.builder()
            .type(type)
            .content(
                String.format(
                    type.name().equals("ENTER") ? "[%s] %s 님이 입장했습니다." : "[%s] %s 님이 퇴장했습니다.",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    message.getSenderName()
                )
            )
            .senderId(message.getSenderId())
            .roomId(message.getRoomId())
            .timestamp(LocalDateTime.now())
            .build();
    }
}
