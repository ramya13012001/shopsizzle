package com.Shop.Sizzle.service.impl;

import com.Shop.Sizzle.exception.APIException;
import com.Shop.Sizzle.exception.ResourceNotFoundException;
import com.Shop.Sizzle.model.Category;
import com.Shop.Sizzle.repository.CategoryRepository;
import com.Shop.Sizzle.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class categoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllcategories() {
        List<Category> savedCategory=categoryRepository.findAll();

        if(savedCategory.size()<1){
            throw new APIException("No category is created till now !");
        }

        return categoryRepository.findAll();
    }

    @Override
    public void createNewCategory(Category category) {

        Category savedCategory=categoryRepository.findBycategoryName(category.getCategoryName());

        if(savedCategory!=null) {
            throw new APIException("Category with "+category.getCategoryName()+" already exists !!!");
        }
            categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category category = categories.orElseThrow(() -> new ResourceNotFoundException("category","categoryId",categoryId));
        categoryRepository.delete(category);
        return "Category with " + categoryId + " is deleted successfully!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category savedCategory = categories.orElseThrow(() ->  new ResourceNotFoundException("category","categoryId",categoryId));
        savedCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(savedCategory);
    }
}
