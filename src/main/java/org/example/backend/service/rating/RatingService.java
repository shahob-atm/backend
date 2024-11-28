package org.example.backend.service.rating;

import org.example.backend.dto.rating.RatingDto;
import org.springframework.http.HttpEntity;

public interface RatingService {

    HttpEntity<?> handlePostRating(RatingDto ratingDto);
}
