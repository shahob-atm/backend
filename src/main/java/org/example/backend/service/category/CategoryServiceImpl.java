package org.example.backend.service.category;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.category.CategoryDto;
import org.example.backend.entity.Category;
import org.example.backend.repository.CategoryRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public HttpEntity<?> handleGetAllCategories() {
        return ResponseEntity.ok(categoryRepo.findAll());
    }

    @Override
    public HttpEntity<?> handlePostCategory(CategoryDto categoryDto) {
        Category category = Category.builder().name(categoryDto.name()).build();
        categoryRepo.save(category);
        return ResponseEntity.ok("Success");
    }

    @Override
    public HttpEntity<?> handlePutCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(id).orElseThrow();
        category.setName(categoryDto.name());
        categoryRepo.save(category);
        return ResponseEntity.ok("Success");
    }
}
