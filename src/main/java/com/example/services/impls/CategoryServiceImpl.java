package com.example.services.impls;

import com.example.dtos.CategoryDtos.CategoryAddDto;
import com.example.dtos.CategoryDtos.CategoryDto;
import com.example.dtos.CategoryDtos.CategoryHomeDto;
import com.example.dtos.CategoryDtos.CategoryUpdateDto;
import com.example.exception.NotFoundExeption;
import com.example.models.Category;
import com.example.repostories.CategoryRepository;
import com.example.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createCategory(CategoryAddDto categoryAddDto) {
        Category category = new Category();
        category.setName(categoryAddDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryHomeDto> getHomeCategories() {
        List<CategoryHomeDto> categories = categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryHomeDto.class)).toList();

        return categories;
    }

    @Override
    public void updateCategory(CategoryUpdateDto categoryUpdateDto,Long id) {
        Category findCategory=categoryRepository.findById(id).orElseThrow(NotFoundExeption::new);
        findCategory.setName(categoryUpdateDto.getName());
        categoryRepository.save(findCategory);
    }
    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(NotFoundExeption::new);
        categoryRepository.deleteById(id);
    }
    @Override
    public CategoryUpdateDto findUpdateCategory(Long id) {
        Category findCategory = categoryRepository.findById(id).orElseThrow(NotFoundExeption::new);
        CategoryUpdateDto result = modelMapper.map(findCategory, CategoryUpdateDto.class);
        return result;
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NotFoundExeption::new);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> result = categories
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
        return result;
    }
}
