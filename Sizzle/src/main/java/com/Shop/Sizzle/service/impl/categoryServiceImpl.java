package com.Shop.Sizzle.service.impl;

import com.Shop.Sizzle.exception.APIException;
import com.Shop.Sizzle.exception.ResourceNotFoundException;
import com.Shop.Sizzle.model.Category;
import com.Shop.Sizzle.payload.CategoryDTO;
import com.Shop.Sizzle.payload.CategoryResponse;
import com.Shop.Sizzle.repository.CategoryRepository;
import com.Shop.Sizzle.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class categoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder){

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize,sortByAndOrder);
        Page categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();
        if (categories.size() == 0) {
            throw new APIException("No category is created till now !");
        }

        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> (modelMapper.map(category, CategoryDTO.class))).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDTOS) {

        Category category = modelMapper.map(categoryDTOS, Category.class);
        Category savedCategory=categoryRepository.findBycategoryName(category.getCategoryName());

        if(savedCategory!=null) {
            throw new APIException("Category with "+category.getCategoryName()+" already exists !!!");
        }
        return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category category = categories.orElseThrow(() -> new ResourceNotFoundException("category","categoryId",categoryId));
        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTOS, Long categoryId) {
        Category category = modelMapper.map(categoryDTOS, Category.class);
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category savedCategory = categories.orElseThrow(() ->  new ResourceNotFoundException("category","categoryId",categoryId));
        savedCategory.setCategoryName(category.getCategoryName());
        return modelMapper.map(categoryRepository.save(savedCategory), CategoryDTO.class);
    }
}
