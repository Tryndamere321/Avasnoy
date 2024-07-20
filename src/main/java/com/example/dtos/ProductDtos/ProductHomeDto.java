package com.example.dtos.ProductDtos;

import com.example.dtos.CategoryDtos.CategoryHomeDto;
import lombok.Data;

@Data
public class ProductHomeDto {
    private Long id;
    private String name;
    private CategoryHomeDto categoryHomeDto;
    private String product;
    private Double price;
    private Double weight;
    private String productType;
    private String photoUrl;
}
