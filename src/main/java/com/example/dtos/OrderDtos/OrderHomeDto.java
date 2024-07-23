package com.example.dtos.OrderDtos;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.ProductDtos.ProductHomeDto;
import lombok.Data;

import java.util.Date;

@Data
public class OrderHomeDto {
    private Long id;
    private String address;
    private String phone;
    private Long userId;
    private CartRequestDto cart;
}
