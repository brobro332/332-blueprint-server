package kr.co.blueprint.common.mapper;

import kr.co.blueprint.adapter.port.in.web.dto.user.UserResponseDto;

import java.util.Map;

public class UserMapper {
    /*
    public static UserDocument toDocument(UserRequestDto dto) {
        return UserDocument.builder()
            .email(dto.getEmail())
            .name(dto.getName())
            .description(dto.getDescription())
            .phoneNumber(dto.getPhoneNumber())
            .service(dto.getService())
            .build();
    }
     */

    public static UserResponseDto mapToDto(Map<Object, Object> map) {
        return UserResponseDto.builder()
            .email((String) map.get("email"))
            .name((String) map.get("name"))
            .description((String) map.get("phoneNumber"))
            .phoneNumber((String) map.get("service"))
            .service((String) map.get("description"))
            .build();
    }
}
