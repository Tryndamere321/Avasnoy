package com.example.services.impls;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.OrderDtos.OrderAddDto;
import com.example.models.Order;
import com.example.repostories.OrderRepository;
import com.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order createOrder(OrderAddDto orderAddDto, CartRequestDto cartRequestDto) {
        Order order = new Order();
        order.setAddress(orderAddDto.getAddress());
        orderRepository.save(order);
        return order;
    }

}
