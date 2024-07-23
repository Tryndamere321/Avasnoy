package com.example.dtos.CartDtos;

import com.example.dtos.ProductDtos.ProductDto;
import com.example.dtos.ProductDtos.ProductRequestDto;
import com.example.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDto {
    private Long id;
    private Double quantity;
    private ProductRequestDto product;
    private Long userId;
    private Double totalPrice;
}
