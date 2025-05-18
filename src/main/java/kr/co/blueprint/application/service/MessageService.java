package kr.co.blueprint.application.service;

import kr.co.blueprint.adapter.port.in.web.dto.message.MessageRequestDto;
import kr.co.blueprint.common.mapper.MessageMapper;
import kr.co.blueprint.domain.entity.message.MessageDocument;
import kr.co.blueprint.domain.port.out.mongodb.message.MessageMongodbPort;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final SimpMessagingTemplate template;
    private final MessageMongodbPort repository;

    public void sendMessage(MessageRequestDto message) {
        MessageDocument inputMessage = MessageMapper.toGeneralMessageDocument(message);

        repository.save(inputMessage);
        template.convertAndSend("/topic/room_" + message.getRoomId(), inputMessage);
    }

    public void sendSystemMessage(MessageRequestDto messageRequestDto) {
        MessageDocument systemMessage = MessageMapper.toSystemMessageDocument(messageRequestDto);

        repository.save(systemMessage);
        template.convertAndSend("/topic/room_" + messageRequestDto.getRoomId(), systemMessage);
    }
}
