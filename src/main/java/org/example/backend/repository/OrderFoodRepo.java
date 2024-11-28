package org.example.backend.repository;

import org.example.backend.entity.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepo extends JpaRepository<OrderFood, Long> {
}
