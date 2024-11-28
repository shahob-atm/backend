package org.example.backend.repository;

import org.example.backend.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepo extends JpaRepository<Rating, Long> {
}
