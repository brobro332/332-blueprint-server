package kr.co.blueprint.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.blueprint.adapter.port.in.web.dto.user.UserRequestDto;
import kr.co.blueprint.adapter.port.in.web.dto.user.UserResponseDto;
import kr.co.blueprint.domain.entity.user.UserDocument;

import java.util.Map;

public class UserMapper {
    public static UserDocument toDocument(UserRequestDto dto) {
        return UserDocument.builder()
            .email(dto.getEmail())
            .name(dto.getName())
            .description(dto.getDescription())
            .phoneNumber(dto.getPhoneNumber())
            .service(dto.getService())
            .build();
    }

    public static UserResponseDto mapToDto(Map<Object, Object> map) {
        return UserResponseDto.builder()
            .email((String) map.get("email"))
            .name((String) map.get("name"))
            .description((String) map.get("phoneNumber"))
            .phoneNumber((String) map.get("service"))
            .service((String) map.get("description"))
            .build();
    }

    public static String toJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 변환 실패", e);
        }
    }
}
