package kr.co.blueprint.adapter.port.in.web.dto.user;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String email;
    private String name;
    private String description;
    private String phoneNumber;
    private String service;
}
