package com.Shop.Sizzle.controller;

import com.Shop.Sizzle.model.Category;
import com.Shop.Sizzle.service.CategoryService;
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

    @RequestMapping(value = "/public/categories", method = RequestMethod.POST)
    public ResponseEntity<String> CreateCategory(@RequestBody Category category) {
        categoryService.createNewCategory(category);
        URI location = URI.create("/api/public/categories/" + category.getCategoryId());
        return ResponseEntity.created(location).body("Category created successfully");
    }

    @RequestMapping(value = "/admin/categories/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable("categoryId") Long categoryId) {
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable("categoryId") Long categoryId) {
        try {
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return ResponseEntity.ok("Category updated successfully " + savedCategory.getCategoryId());
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
