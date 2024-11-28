package org.example.backend.dto.rating;

import jakarta.validation.constraints.NotNull;

public record RatingDto(
        @NotNull
        Integer quantity,
        @NotNull
        Long foodId
) {
}
