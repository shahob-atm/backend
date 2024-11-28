package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.auth.LoginDto;
import org.example.backend.dto.auth.RegisterDto;
import org.example.backend.service.auth.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody @Validated RegisterDto registerDto) {
        return authService.registerUser(registerDto);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@RequestBody @Validated LoginDto loginDto) {
        return authService.loginUser(loginDto);
    }

    @GetMapping("/me")
    public HttpEntity<?> me() {
        return authService.me();
    }
}
