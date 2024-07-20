package com.example.dtos.ProductDtos;

import com.example.dtos.CategoryDtos.CategoryHomeDto;
import lombok.Data;

@Data
public class ProductDashboardDto {
    private Long id;
    private String name;
    private CategoryHomeDto category;
    private String product;
    private Double price;
    private Double weight;
    private String productType;
    private String photoUrl;
}
