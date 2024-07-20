package com.example.dtos.ProductDtos;

import lombok.Data;

@Data
public class ProductUpdateDto {
    private Long categoryId;
    private String product;
    private Double price;
    private Double weight;
    private String photoUrl;
}
