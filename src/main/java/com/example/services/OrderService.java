package com.example.services;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.OrderDtos.OrderAddDto;
import com.example.dtos.OrderDtos.OrderHomeDto;
import com.example.models.Order;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

public interface OrderService {
     Order createOrder(OrderAddDto orderAddDto,String email);
     List<OrderHomeDto> getDashboardOrders();
     void completeOrder(Long id);
     void cancelOrder(Long id);
}
