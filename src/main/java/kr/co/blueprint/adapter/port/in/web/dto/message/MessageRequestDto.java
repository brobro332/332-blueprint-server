package kr.co.blueprint.adapter.port.in.web.dto.message;

import kr.co.blueprint.domain.entity.message.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class MessageRequestDto {
    private String messageId;
    private Long senderId;
    private Long roomId;
    private String content;
    private String senderName;
    private MessageType type;
    private LocalDateTime sendAt;
}
