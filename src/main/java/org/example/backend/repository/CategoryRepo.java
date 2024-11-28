package org.example.backend.repository;

import org.example.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
