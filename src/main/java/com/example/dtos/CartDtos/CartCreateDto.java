package com.example.dtos.CartDtos;

import com.example.dtos.ProductDtos.ProductDto;
import lombok.Data;

@Data
public class CartCreateDto {
    private Double quantity;
    private Long productId;
    private Double totalPrice;
}
