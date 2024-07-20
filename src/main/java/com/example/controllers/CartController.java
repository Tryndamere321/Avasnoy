package com.example.controllers;

import com.example.dtos.CartDtos.CartCreateDto;
import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.ProductDtos.ProductDashboardDto;
import com.example.models.Cart;
import com.example.models.Product;
import com.example.services.CartService;
import com.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
@GetMapping("/cart")
    public String cart(Model model) {
    List<CartRequestDto> carts = cartService.getProducts();
    model.addAttribute("carts", carts);
    return "/cart";
}

//    @PostMapping("/cart")
//    public String addToCart(CartCreateDto cartCreateDto, Model model) {
//        cartService.addCart(cartCreateDto);
//        return "redirect:/";
//    }

}
