package org.example.backend.service.food;

import org.example.backend.dto.food.FoodDto;
import org.springframework.http.HttpEntity;

public interface FoodService {

    HttpEntity<?> handleGetFoods(Long categoryId);

    HttpEntity<?> handleAddFood(FoodDto foodDto);

    HttpEntity<?> handleUpdateFood(Long id, FoodDto foodDto);

    HttpEntity<?> handleDeleteFood(Long id);
}
