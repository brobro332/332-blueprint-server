package kr.co.samsami_hub.websocket.service;

import kr.co.samsami_hub.websocket.Message;
import kr.co.samsami_hub.websocket.MessageRepository;
import kr.co.samsami_hub.websocket.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final SimpMessagingTemplate template;
    private final MessageRepository repository;

    public void sendMessage(Message message) {
        Long roomId = message.getRoomId();

        Message inputMessage = Message.builder()
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

    public void sendSystemMessage(Message message) {
        MessageType type = message.getType();
        Long roomId = message.getRoomId();

        Message systemMessage = Message.builder()
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
