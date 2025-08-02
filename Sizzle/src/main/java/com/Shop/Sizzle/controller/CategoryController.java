package com.Shop.Sizzle.controller;

import com.Shop.Sizzle.model.Category;
import com.Shop.Sizzle.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllcategories();
    }

    @PostMapping("/api/public/categories")
    public String CreateCategory(@RequestBody Category category) {
        categoryService.createNewCategory(category);
        return "category successfully added";
    }
}
