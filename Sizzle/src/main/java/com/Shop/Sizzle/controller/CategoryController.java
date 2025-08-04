package com.Shop.Sizzle.controller;

import com.Shop.Sizzle.model.Category;
import com.Shop.Sizzle.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allcategories = categoryService.getAllcategories();
        return new ResponseEntity<>(allcategories, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> CreateCategory(@Valid @RequestBody Category category) {
        categoryService.createNewCategory(category);
        return new ResponseEntity<>("Category created successfully",HttpStatus.CREATED);
    }

    @DeleteMapping( "/admin/categories/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable("categoryId") Long categoryId) {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable("categoryId") Long categoryId) {
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return ResponseEntity.ok("Category updated successfully " + savedCategory.getCategoryId());
    }
}
