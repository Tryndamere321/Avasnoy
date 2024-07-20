package com.example.dtos.CartDtos;

import lombok.Data;

@Data
public class CartHomeDto {
    private Long id;
    private Double quantity;
    private Long productId;
    private Long userId;
    private Double totalPrice;
}
