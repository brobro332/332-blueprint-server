package kr.co.blueprint.adapter.port.in.web.controller.message;

import kr.co.blueprint.adapter.port.in.web.dto.message.MessageRequestDto;
import kr.co.blueprint.application.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@RequestBody MessageRequestDto message) {
        service.sendMessage(message);
    }

    @MessageMapping("/chat.sendSystemMessage")
    public void enterUser(@RequestBody MessageRequestDto message) {
        service.sendSystemMessage(message);
    }
}
