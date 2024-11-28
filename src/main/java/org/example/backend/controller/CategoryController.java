package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.category.CategoryDto;
import org.example.backend.service.category.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> handleGetAllCategories() {
        return categoryService.handleGetAllCategories();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public HttpEntity<?> handlePostCategory(@RequestBody @Validated CategoryDto categoryDto) {
        return categoryService.handlePostCategory(categoryDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public HttpEntity<?> handlePutCategory(@PathVariable Long id, @RequestBody @Validated CategoryDto categoryDto) {
        return categoryService.handlePutCategory(id, categoryDto);
    }
}
