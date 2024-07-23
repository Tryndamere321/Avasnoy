package com.example.dtos.ProductDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductUpdateDto {
    private Long categoryId;
    @NotBlank(message = "Name is mandatory")
    private String product;
    @NotBlank(message = "Name is mandatory")
    private Double price;
    @NotBlank(message = "Name is mandatory")
    private Double weight;
    @NotBlank(message = "Name is mandatory")
    private String photoUrl;
}
