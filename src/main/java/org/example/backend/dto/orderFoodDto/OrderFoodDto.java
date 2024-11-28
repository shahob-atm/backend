package org.example.backend.dto.orderFoodDto;

public record OrderFoodDto(
        Long foodId,
        Double price,
        Integer count
) {
}
