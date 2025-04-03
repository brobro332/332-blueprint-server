package kr.co.samsami_hub.websocket.controller;

import kr.co.samsami_hub.websocket.object.WebSocketMessage;
import kr.co.samsami_hub.websocket.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class WebSocketController {
    private final WebSocketService service;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public WebSocketMessage sendMessage(WebSocketMessage webSocketMessage) {
        return webSocketMessage;
    }

    @MessageMapping("/chat.sendSystemMessage")
    @SendTo("/topic/public")
    public WebSocketMessage enterUser(WebSocketMessage webSocketMessage) {
        return service.createSystemMessage(webSocketMessage);
    }
}
