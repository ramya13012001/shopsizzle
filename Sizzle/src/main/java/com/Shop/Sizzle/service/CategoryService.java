package com.Shop.Sizzle.service;

import com.Shop.Sizzle.payload.CategoryDTO;
import com.Shop.Sizzle.payload.CategoryResponse;

public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize , String sortBy,String sortaOrder);

    CategoryDTO createNewCategory(CategoryDTO categoryDTOS);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTOS, Long categoryId);
}
