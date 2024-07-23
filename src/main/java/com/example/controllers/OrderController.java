package com.example.controllers;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.OrderDtos.OrderAddDto;
import com.example.models.Order;
import com.example.services.OrderService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String order() {

        return "/order";
    }

    @PostMapping("/order")
    public String createOrder(@ModelAttribute OrderAddDto orderAddDto,Principal principal,Model model) {
        String username = principal.getName();
        Order order = orderService.createOrder(orderAddDto, username);
        model.addAttribute("order", order);
        return "redirect:/order";
    }
    //List<Long> productIds
}
