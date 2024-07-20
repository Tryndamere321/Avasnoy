package com.example.services;

import com.example.dtos.CategoryDtos.CategoryAddDto;
import com.example.dtos.CategoryDtos.CategoryDto;
import com.example.dtos.CategoryDtos.CategoryHomeDto;
import com.example.dtos.CategoryDtos.CategoryUpdateDto;
import com.example.models.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryAddDto categoryAddDto);
    List<CategoryHomeDto> getHomeCategories();
    void updateCategory(CategoryUpdateDto categoryUpdateDto, Long id);
    public void deleteCategory(Long id);
    CategoryUpdateDto findUpdateCategory(Long id);
    public Category findCategoryById(Long id);
    List<CategoryDto> getCategories();
}
