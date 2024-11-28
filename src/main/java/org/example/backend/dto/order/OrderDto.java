package org.example.backend.dto.order;

import org.example.backend.dto.orderFoodDto.OrderFoodDto;

import java.util.List;

public record OrderDto(
        List<OrderFoodDto> orderFoodDto
) {
}
