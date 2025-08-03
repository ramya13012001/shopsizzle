package com.Shop.Sizzle.service;

import com.Shop.Sizzle.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllcategories();

    void createNewCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
