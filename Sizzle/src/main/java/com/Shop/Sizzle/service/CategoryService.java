package com.Shop.Sizzle.service;

import com.Shop.Sizzle.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllcategories();

    void createNewCategory(Category category);

}
