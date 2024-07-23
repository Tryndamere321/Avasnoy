package com.example.dtos.OrderDtos;

import com.example.dtos.CartDtos.CartDto;
import com.example.dtos.CartDtos.CartRequestDto;
import lombok.Data;

import java.util.Date;

@Data
public class OrderAddDto {
    private String address;
    private Long userId;
    private CartRequestDto cart;
}
