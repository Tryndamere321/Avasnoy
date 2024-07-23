package com.example.controllers;

import com.example.dtos.CartDtos.CartCreateDto;
import com.example.dtos.CategoryDtos.CategoryDto;
import com.example.dtos.CategoryDtos.CategoryHomeDto;
import com.example.dtos.ProductDtos.ProductAddDto;
import com.example.dtos.ProductDtos.ProductDashboardDto;
import com.example.dtos.ProductDtos.ProductDetailDto;
import com.example.dtos.ProductDtos.ProductUpdateDto;
import com.example.models.Cart;
import com.example.repostories.ProductRepository;
import com.example.services.CartService;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
    @ Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;

    @GetMapping("/dashboard/article")
    public String index(Model model) {
        List<ProductDashboardDto> articles = productService.getDashboardArticles();
        model.addAttribute("articles", articles);
        return "/dashboard/article/index";

    }

   @GetMapping("/article/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        ProductDetailDto articleDetail = productService.getDetail(id);
        model.addAttribute("article", articleDetail);
        return "/detail";
    }
    @PostMapping("/article/detail/{id}")
    public String addCart(CartCreateDto cartCreateDto, Principal principal, @PathVariable Long id){
        String name = "Res";
        cartCreateDto.setQuantity(1D);
        String username = principal.getName();
        cartService.addCart(cartCreateDto,username);
        return "redirect:/article/detail/"+cartCreateDto.getProductId();
    }






    @GetMapping("/dashboard/article/create")
    public String create(Model model) {
        List<CategoryHomeDto> categories = categoryService.getHomeCategories();
        model.addAttribute("categories", categories);
        return "/dashboard/article/create";
    }

    @PostMapping("/dashboard/article/create")
    public String create(@Valid ProductAddDto articleCreateDto, @RequestParam("image") MultipartFile file) throws IOException {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        articleCreateDto.setPhotoUrl(rand + fileNames.toString());
        productService.CreateArticle(articleCreateDto);
        return "redirect:/dashboard/article";
    }

    @GetMapping("/dashboard/article/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        return "/dashboard/article/delete";
    }

    @PostMapping("/dashboard/article/delete/{id}")
    public String removeArticle(@PathVariable Long id) {
        productService.removeArticle(id);
        return "redirect:/dashboard/article";
    }

    @GetMapping("/dashboard/article/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        ProductUpdateDto article = productService.findUpdateArticle(id);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("article", article);
        model.addAttribute("categories", categories);
        return "/dashboard/article/update";


    }

    @PostMapping("/dashboard/article/update/{id}")
    public String updateArticle(@PathVariable Long id, ProductUpdateDto articleUpdateDto, @RequestParam("image") MultipartFile file) throws IOException {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        articleUpdateDto.setPhotoUrl(rand + fileNames.toString());
        productService.updateArticle(articleUpdateDto, id);
        return "redirect:/dashboard/article";
    }


}
