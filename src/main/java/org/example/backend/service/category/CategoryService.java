package org.example.backend.service.category;

import org.example.backend.dto.category.CategoryDto;
import org.springframework.http.HttpEntity;

public interface CategoryService {

    HttpEntity<?> handleGetAllCategories();

    HttpEntity<?> handlePostCategory(CategoryDto categoryDto);

    HttpEntity<?> handlePutCategory(Long id, CategoryDto categoryDto);
}
