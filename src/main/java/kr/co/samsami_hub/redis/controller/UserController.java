package kr.co.samsami_hub.redis.controller;

import kr.co.samsami_hub.redis.dto.UserDto;
import kr.co.samsami_hub.redis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) {
        UserDto user = service.getUser(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> getAllUserPage(
        @PageableDefault(
            size = 10, sort = "service", direction = Sort.Direction.DESC
        ) Pageable pageable
    ) {
        Page<UserDto> allUserListPage = service.getAllUserListPage(pageable);
        return ResponseEntity.ok(allUserListPage);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        service.deleteUser(email);
        return ResponseEntity.ok().build();
    }
}
