package org.example.backend.service.rating;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.rating.RatingDto;
import org.example.backend.entity.Food;
import org.example.backend.entity.Rating;
import org.example.backend.repository.FoodRepo;
import org.example.backend.repository.RatingRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;
    private final FoodRepo foodRepo;

    @Override
    public HttpEntity<?> handlePostRating(RatingDto ratingDto) {
        Food food = foodRepo.findById(ratingDto.foodId()).orElseThrow();
        Rating rating = Rating.builder().food(food).quantity(ratingDto.quantity()).build();
        ratingRepo.save(rating);
        return ResponseEntity.ok("Success");
    }
}
