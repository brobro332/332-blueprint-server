package kr.co.blueprint.adapter.port.in.web.controller;

import kr.co.blueprint.infrastructure.mongodb.entity.MessageDocument;
import kr.co.blueprint.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(MessageDocument messageDocument) {
        service.sendMessage(messageDocument);
    }

    @MessageMapping("/chat.sendSystemMessage")
    public void enterUser(MessageDocument messageDocument) {
        service.sendSystemMessage(messageDocument);
    }
}
