package com.example.controllers;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.OrderDtos.OrderAddDto;
import com.example.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    private OrderService orderService;

    @GetMapping("/order")
    public String order() {

        return "/order";
    }

    @PostMapping("/order")
    public String processOrder(@ModelAttribute OrderAddDto orderAddDto,@ModelAttribute CartRequestDto cartRequestDto) {
        orderService.createOrder(orderAddDto,cartRequestDto);
        return "redirect:/orderConfirmation";
    }
}
