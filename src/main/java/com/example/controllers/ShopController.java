package com.example.controllers;

import com.example.dtos.ProductDtos.ProductHomeDto;
import com.example.payloads.ArticlePagination;
import com.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ShopController {
    @Autowired
    private ProductService productService;
    @GetMapping("/shop")
    public String shop(Model model,@RequestParam(value = "currentPage", required = false) Integer currentPage) {
        ArticlePagination articles = productService.getHomePaginationArticle(currentPage);
        model.addAttribute("articles", articles.getArticles());
        model.addAttribute("pagination", articles.getPageSize());
        return "/shop";
    }
}
