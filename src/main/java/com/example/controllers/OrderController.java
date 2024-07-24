package com.example.controllers;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.OrderDtos.OrderAddDto;
import com.example.models.Order;
import com.example.services.OrderService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/myOrders")
    public String myOrders(Model model) {
        orderService.getDashboardOrders();
        model.addAttribute("orders", orderService.getDashboardOrders());
        return "/myOrders";
    }
    //List<Long> productIds
    @GetMapping("order/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "redirect:/myOrders";
    }

    @GetMapping("order/complete/{id}")
    public String completeOrder(@PathVariable Long id) {
        orderService.completeOrder(id);
        return "redirect:/dashboard/orders";
    }
    @GetMapping("/dashboard/orders")
    public String index(Model model) {
        model.addAttribute("orders", orderService.getDashboardOrders());
        return "/dashboard/orders/index";
    }
}
