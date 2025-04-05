package kr.co.samsami_hub.redis.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    @NonNull
    private String email;
    private String name;
    private String description;
    private String phoneNumber;
}
