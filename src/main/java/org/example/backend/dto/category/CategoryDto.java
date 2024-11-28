package org.example.backend.dto.category;

import jakarta.validation.constraints.NotEmpty;

public record CategoryDto(
        @NotEmpty
        String name
) {
}
