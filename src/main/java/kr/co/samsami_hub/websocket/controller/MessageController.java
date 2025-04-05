package kr.co.samsami_hub.websocket.controller;

import kr.co.samsami_hub.websocket.Message;
import kr.co.samsami_hub.websocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(Message message) {
        service.sendMessage(message);
    }

    @MessageMapping("/chat.sendSystemMessage")
    public void enterUser(Message message) {
        service.sendSystemMessage(message);
    }
}
