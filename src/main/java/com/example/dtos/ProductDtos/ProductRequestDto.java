package com.example.dtos.ProductDtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductRequestDto {
    private Long id;
    private String product;
    private String photoUrl;
    private Double price;
}
