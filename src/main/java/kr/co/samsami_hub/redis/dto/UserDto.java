package kr.co.samsami_hub.redis.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NonNull
    private String email;
    private String name;
    private String description;
    private String phoneNumber;
    private String service;
}
