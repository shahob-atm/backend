package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.order.OrderDto;
import org.example.backend.service.order.OrderService;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public HttpEntity<?> handleGetOrder(){
        return orderService.handleGetOrder();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public HttpEntity<?> handlePostOrder(@RequestBody OrderDto orderDto){
        return orderService.handlePostOrder(orderDto);
    }
}
