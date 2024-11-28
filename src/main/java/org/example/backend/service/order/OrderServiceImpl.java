package org.example.backend.service.order;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.order.OrderDto;
import org.example.backend.dto.orderFoodDto.OrderFoodDto;
import org.example.backend.entity.*;
import org.example.backend.repository.FoodRepo;
import org.example.backend.repository.OrderFoodRepo;
import org.example.backend.repository.OrderRepo;
import org.example.backend.repository.UserRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final FoodRepo foodRepo;
    private final OrderFoodRepo orderFoodRepo;

    @Override
    public HttpEntity<?> handleGetOrder() {
        return ResponseEntity.ok(orderRepo.getOrderProjections());
    }

    @Override
    public HttpEntity<?> handlePostOrder(OrderDto orderDto) {
        if (orderDto.orderFoodDto().isEmpty()) throw new RuntimeException("Order food is empty");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepo.findByUsername(name).orElseThrow();
        Order order = Order.builder().user(user).status(Status.SHIPPED).build();
        Order saved = orderRepo.save(order);

        for (OrderFoodDto orderFoodDto : orderDto.orderFoodDto()) {
            Food food = foodRepo.findById(orderFoodDto.foodId()).orElseThrow();
            OrderFood orderFood = OrderFood.builder().food(food).price((Double) orderFoodDto.price()).count(orderFoodDto.count()).order(saved).build();
            orderFoodRepo.save(orderFood);
        }

        return ResponseEntity.ok(saved);
    }
}
