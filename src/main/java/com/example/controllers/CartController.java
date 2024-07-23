package com.example.controllers;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.OrderDtos.OrderAddDto;
import com.example.models.Order;
import com.example.services.CartService;
import com.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        String username = principal.getName();
        List<CartRequestDto> carts = cartService.getProducts();
        double subtotal = carts.stream()
                .mapToDouble(cart -> cart.getQuantity() * cart.getProduct().getPrice())
                .sum();
        double shipping = 15.0;
        double total = subtotal + shipping;

        model.addAttribute("carts", carts);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("shipping", shipping);
        model.addAttribute("total", total);
        return "/cart";
    }

//    @PostMapping("/cart")
//    public String getTotalPrice(@RequestBody CartRequestDto cartRequestDto, Model model) {
//        Double totalPrice = cartService.calculateTotalPrice(cartRequestDto);
//        model.addAttribute("totalPrice", totalPrice);
//        return "redirect:/order";
//
//    }



//    @PostMapping("/cart")
//    public String order(Model model, Principal principal, OrderAddDto orderAddDto, CartRequestDto cartRequestDto, Long userId) {
//
//        return "redirect:/order";
//    }


    @PostMapping("/cart/{id}")
    public String handleCartAction(@PathVariable Long id, @RequestParam String action, @ModelAttribute CartRequestDto cartRequestDto,@ModelAttribute OrderAddDto orderAddDto) {
        if ("remove".equals(action)) {
            cartService.deleteProduct(id);
        } else if ("update".equals(action)) {
            cartService.updateCart(cartRequestDto, id);
        }
        return "redirect:/cart";
    }
}


