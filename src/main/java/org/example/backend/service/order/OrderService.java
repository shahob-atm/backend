package org.example.backend.service.order;

import org.example.backend.dto.order.OrderDto;
import org.springframework.http.HttpEntity;

public interface OrderService {

    HttpEntity<?> handleGetOrder();

    HttpEntity<?> handlePostOrder(OrderDto orderDto);
}
