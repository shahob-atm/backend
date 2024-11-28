package org.example.backend.dto.food;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FoodDto(
        @NotEmpty
        String name,
        @NotEmpty
        String description,
        @NotNull
        Double price,
        @NotEmpty
        String imageUrl,
        @NotNull
        Long categoryId
) {
}
