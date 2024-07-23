package com.example.controllers;

import com.example.dtos.CategoryDtos.CategoryAddDto;
import com.example.dtos.CategoryDtos.CategoryHomeDto;
import com.example.dtos.CategoryDtos.CategoryUpdateDto;
import com.example.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @GetMapping("/dashboard/category")
    public String dashboardCategory(Model model) {
        List<CategoryHomeDto> data = categoryService.getHomeCategories();
        model.addAttribute("categories", data);
        return "/dashboard/category/index";
    }

    @GetMapping("/dashboard/category/create")
    public String addCategory() {
        return "/dashboard/category/create";
    }

    @PostMapping("/dashboard/category/create")
    public String addCategory(@Valid CategoryAddDto categoryAddDto) {
        categoryService.createCategory(categoryAddDto);
        return "redirect:/dashboard/category";
    }

    @GetMapping("/dashboard/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        return "/dashboard/category/delete";
    }

    @PostMapping("/dashboard/category/delete/{id}")
    public String removeCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/dashboard/category";
    }

    @GetMapping("/dashboard/category/update/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        CategoryUpdateDto category = categoryService.findUpdateCategory(id);
        model.addAttribute("category", category);
        return "/dashboard/category/update";
    }

    @PostMapping("/dashboard/category/update/{id}")
    public String updateCategory(@PathVariable Long id, CategoryUpdateDto categoryUpdateDto) {
        categoryService.updateCategory(categoryUpdateDto, id);
        return "redirect:/dashboard/category";
    }
}
