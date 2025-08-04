package com.Shop.Sizzle.service.impl;

import com.Shop.Sizzle.model.Category;
import com.Shop.Sizzle.repository.CategoryRepository;
import com.Shop.Sizzle.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class categoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllcategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createNewCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category category = categories.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryRepository.delete(category);
        return "Category with " + categoryId + " is deleted successfully!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category savedCategory = categories.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        savedCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(savedCategory);
    }
}
