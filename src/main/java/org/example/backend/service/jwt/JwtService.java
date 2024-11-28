package org.example.backend.service.jwt;

import org.example.backend.entity.User;

public interface JwtService {

    String generateJwtToken(User user);

    String extractJwtToken(String token);
}
