package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UpdateUserRequest;
import org.example.model.User;
import org.example.service.AuthService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return authService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest req
    ) {
        return authService.updateUser(id, req);
    }
}
