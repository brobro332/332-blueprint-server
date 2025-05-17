package kr.co.blueprint.common.mapper;

import kr.co.blueprint.adapter.port.in.web.dto.message.MessageRequestDto;
import kr.co.blueprint.domain.entity.message.MessageType;
import kr.co.blueprint.domain.entity.message.MessageDocument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageMapper {
    public static MessageDocument toGeneralMessageDocument(MessageRequestDto messageRequestDto) {
        return MessageDocument.builder()
            .messageId(messageRequestDto.getMessageId())
            .senderId(messageRequestDto.getSenderId())
            .roomId(messageRequestDto.getRoomId())
            .content(
                String.format(
                    "[%s] %s : %s",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    messageRequestDto.getSenderName(),
                    messageRequestDto.getContent()
                )
            )
            .senderName(messageRequestDto.getSenderName())
            .type(messageRequestDto.getType())
            .sendAt(messageRequestDto.getSendAt())
            .build();
    }

    public static MessageDocument toSystemMessageDocument(MessageRequestDto messageRequestDto) {
        MessageType type = messageRequestDto.getType();

        return MessageDocument.builder()
            .messageId(messageRequestDto.getMessageId())
            .senderId(messageRequestDto.getSenderId())
            .roomId(messageRequestDto.getRoomId())
            .content(
                String.format(
                    type.name().equals("ENTER") ? "[%s] %s 님이 입장했습니다." : "[%s] %s 님이 퇴장했습니다.",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    messageRequestDto.getSenderName()
                )
            )
            .senderName("SYSTEM")
            .type(messageRequestDto.getType())
            .sendAt(messageRequestDto.getSendAt())
            .build();
    }
}
