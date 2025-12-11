package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.AuthResponse;
import org.example.dto.LoginRequest;
import org.example.dto.RegisterRequest;
import org.example.model.User;
import org.example.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) {
        User user = authService.register(request);
        return new AuthResponse(user.getId(), user.getName(), user.getEmail());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        User user = authService.login(request);
        return new AuthResponse(user.getId(), user.getName(), user.getEmail());
    }
}
