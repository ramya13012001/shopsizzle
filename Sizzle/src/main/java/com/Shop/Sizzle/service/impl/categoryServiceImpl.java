package com.Shop.Sizzle.service.impl;

import com.Shop.Sizzle.model.Category;
import com.Shop.Sizzle.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class categoryServiceImpl implements CategoryService {

    private final List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllcategories() {
        return categories;
    }

    @Override
    public void createNewCategory(Category category) {
        categories.add(category);
    }
}
