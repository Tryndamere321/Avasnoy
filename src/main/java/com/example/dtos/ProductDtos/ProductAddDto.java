package com.example.dtos.ProductDtos;

import lombok.Data;

@Data
public class ProductAddDto {
    private String name;
    private Long categoryId;
    private String product;
    private Double price;
    private Double weight;
    private String productType;
    private String photoUrl;
}
