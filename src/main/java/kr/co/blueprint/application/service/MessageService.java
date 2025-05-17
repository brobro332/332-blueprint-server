package kr.co.blueprint.application.service;

import kr.co.blueprint.infrastructure.mongodb.entity.MessageDocument;
import kr.co.blueprint.infrastructure.mongodb.repository.MessageMongodbRepository;
import kr.co.blueprint.domain.model.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final SimpMessagingTemplate template;
    private final MessageMongodbRepository repository;

    public void sendMessage(MessageDocument message) {
        Long roomId = message.getRoomId();

        MessageDocument inputMessage = MessageDocument.builder()
            .type(message.getType())
            .content(
                String.format(
                    "[%s] %s : %s",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    message.getSenderName(),
                    message.getContent()
                )
            )
            .senderId(message.getSenderId())
            .senderName(message.getSenderName())
            .roomId(roomId)
            .sendAt(LocalDateTime.now())
            .build();

        repository.save(inputMessage);

        template.convertAndSend("/topic/room_" + roomId, inputMessage);
    }

    public void sendSystemMessage(MessageDocument message) {
        MessageType type = message.getType();
        Long roomId = message.getRoomId();

        MessageDocument systemMessage = MessageDocument.builder()
            .type(type)
            .content(
                String.format(
                    type.name().equals("ENTER") ? "[%s] %s 님이 입장했습니다." : "[%s] %s 님이 퇴장했습니다.",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    message.getSenderName()
                )
            )
            .senderId(message.getSenderId())
            .senderName("SYSTEM")
            .roomId(roomId)
            .sendAt(LocalDateTime.now())
            .build();

        repository.save(systemMessage);

        template.convertAndSend("/topic/room_" + roomId, systemMessage);
    }
}
