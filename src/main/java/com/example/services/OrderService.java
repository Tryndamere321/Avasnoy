package com.example.services;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.OrderDtos.OrderAddDto;
import com.example.models.Order;

public interface OrderService {
    Order createOrder(OrderAddDto orderAddDto, CartRequestDto cartRequestDto);
}
