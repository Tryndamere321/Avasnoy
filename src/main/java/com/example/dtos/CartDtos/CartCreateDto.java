package com.example.dtos.CartDtos;

import com.example.dtos.ProductDtos.ProductDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CartCreateDto {
    @NotBlank(message = "Name is mandatory")
    private Double quantity;
    @NotBlank(message = "Name is mandatory")
    private Long productId;
    @NotBlank(message = "Name is mandatory")
    private Double totalPrice;
}
