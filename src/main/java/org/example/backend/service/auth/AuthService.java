package org.example.backend.service.auth;

import org.example.backend.dto.auth.LoginDto;
import org.example.backend.dto.auth.RegisterDto;
import org.springframework.http.HttpEntity;

public interface AuthService {

    HttpEntity<?> registerUser(RegisterDto registerDto);

    HttpEntity<?> loginUser(LoginDto loginDto);

    HttpEntity<?> me();
}
