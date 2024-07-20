package com.example.controllers;

import com.example.dtos.ProductDtos.ProductHomeDto;
import com.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        List<ProductHomeDto> products = productService.get5Articles();
        model.addAttribute("articles", products);
        return "/home";
    }
    @GetMapping("/contact")
    public String contact() {
        return "/contact";
    }
}
