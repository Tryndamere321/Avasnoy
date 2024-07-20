package com.example.dtos.ProductDtos;

import lombok.Data;

@Data
public class ProductRequestDto {
    private Long id;
    private String product;
    private String photoUrl;
    private Double price;
}
