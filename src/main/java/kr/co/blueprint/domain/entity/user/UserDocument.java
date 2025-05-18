package kr.co.blueprint.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDocument {
    @Id
    private String email;
    private String name;
    private String description;
    private String phoneNumber;
    private String service;
}
