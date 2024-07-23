package com.example.dtos.CategoryDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryHomeDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Name is mandatory")
    private Long id;
}
