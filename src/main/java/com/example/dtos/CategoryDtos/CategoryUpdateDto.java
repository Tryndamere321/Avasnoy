package com.example.dtos.CategoryDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryUpdateDto {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
}
