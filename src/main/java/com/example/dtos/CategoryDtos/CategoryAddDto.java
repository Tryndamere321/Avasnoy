package com.example.dtos.CategoryDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryAddDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
