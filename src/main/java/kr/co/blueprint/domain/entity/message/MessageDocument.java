package kr.co.blueprint.domain.entity.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDocument {

    @Id
    private String messageId;
    private Long senderId;
    private Long roomId;
    private String content;
    private String senderName;
    private MessageType type;
    private LocalDateTime sendAt;
}
