package org.example.backend.dto.auth;

import jakarta.validation.constraints.NotEmpty;

public record RegisterDto(
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,
        @NotEmpty
        String username,
        @NotEmpty
        String password
) {
}
