package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.rating.RatingDto;
import org.example.backend.service.rating.RatingService;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public HttpEntity<?> handlePostRating(@RequestBody @Validated RatingDto ratingDto) {
        return ratingService.handlePostRating(ratingDto);
    }
}
