package org.example.backend.service.food;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.food.FoodDto;
import org.example.backend.entity.Category;
import org.example.backend.entity.Food;
import org.example.backend.repository.CategoryRepo;
import org.example.backend.repository.FoodRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepo foodRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public HttpEntity<?> handleGetFoods(Long categoryId) {
        if (categoryId != null) {
            return ResponseEntity.ok(foodRepo.getFoodProjectionByCategory(categoryId));
        }
        return ResponseEntity.ok(foodRepo.getAllFoodProjection());
    }

    @Override
    public HttpEntity<?> handleAddFood(FoodDto foodDto) {
        Category category = categoryRepo.findById(foodDto.categoryId()).orElseThrow();
        Food food = Food.builder()
                .name(foodDto.name())
                .description(foodDto.description())
                .price(foodDto.price())
                .imageUrl(foodDto.imageUrl())
                .category(category).build();
        foodRepo.save(food);
        return ResponseEntity.ok("Success");
    }

    @Override
    public HttpEntity<?> handleUpdateFood(Long id, FoodDto foodDto) {
        Category category = categoryRepo.findById(foodDto.categoryId()).orElseThrow();
        Food food = foodRepo.findById(id).orElseThrow();
        food.setName(foodDto.name());
        food.setDescription(foodDto.description());
        food.setPrice(foodDto.price());
        food.setImageUrl(foodDto.imageUrl());
        food.setCategory(category);
        foodRepo.save(food);
        return ResponseEntity.ok("Success");
    }

    @Override
    public HttpEntity<?> handleDeleteFood(Long id) {
        foodRepo.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
