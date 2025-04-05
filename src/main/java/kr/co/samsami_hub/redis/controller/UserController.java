package kr.co.samsami_hub.redis.controller;

import kr.co.samsami_hub.redis.dto.UserDto;
import kr.co.samsami_hub.redis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody @Validated UserDto user) {
        service.setUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestBody @Validated UserDto dto) {
        UserDto user = service.getUser(dto);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody @Validated UserDto dto) {
        service.deleteUser(dto);
        return ResponseEntity.ok().build();
    }
}
