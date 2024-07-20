package com.example.dtos.ProductDtos;

import com.example.dtos.CategoryDtos.CategoryHomeDto;
import lombok.Data;

@Data
public class  ProductDto {
    private Long id;
    private CategoryHomeDto categoryHomeDto;
    private String product;
    private Double price;
    private Double weight;
    private String photoUrl;
}
