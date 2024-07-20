package com.example.dtos.CartDtos;

import com.example.dtos.ProductDtos.ProductDto;
import lombok.Data;

@Data
public class CartDto {
    private Long id;
    private Double quantity;
    private Long productId;
    private Long userId;
    private Double totalPrice;

}
