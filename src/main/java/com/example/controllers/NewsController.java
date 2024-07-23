package com.example.controllers;


import com.example.dtos.CartDtos.CartCreateDto;
import com.example.dtos.CategoryDtos.CategoryHomeDto;
import com.example.dtos.CommentDtos.CommentCreateDto;
import com.example.dtos.CommentDtos.CommentDto;
import com.example.dtos.NewsDtos.NewsCreateDto;
import com.example.dtos.NewsDtos.NewsHomeDto;
import com.example.dtos.NewsDtos.NewsUpdateDto;
import com.example.dtos.ProductDtos.ProductAddDto;
import com.example.dtos.ProductDtos.ProductDetailDto;
import com.example.dtos.ProductDtos.ProductUpdateDto;
import com.example.payloads.ArticlePagination;
import com.example.payloads.NewsPagination;
import com.example.services.CommentService;
import com.example.services.NewsService;
import com.example.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class NewsController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/news")
    public String shop(Model model,@RequestParam(value = "currentPage", required = false) Integer currentPage) {
        NewsPagination news = newsService.getHomePaginationNews(currentPage);
        model.addAttribute("news", news.getArticles());
        model.addAttribute("pagination", news.getPageSize());
        return "/news";
    }
    @GetMapping("/news/news_detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        NewsHomeDto newsDetail = newsService.getDetail(id);
        List<CommentDto> commentDto=commentService.getCommentsByNewsId(id);
        model.addAttribute("news", newsDetail);
        model.addAttribute("comments", commentDto);
        return "/news_detail";
    }
    @PostMapping("/news/news_detail/{id}")
    public String addcomment( CommentCreateDto commentCreateDto, Principal principal, @PathVariable Long id) {
        String username = principal.getName();
        commentCreateDto.setNewsId(id);
        commentService.addComment(commentCreateDto, username);
        return "redirect:/news/news_detail/" + commentCreateDto.getNewsId();
    }

    @GetMapping("/dashboard/news")
    public String dashboardNews(Model model) {
        List<NewsHomeDto> data = newsService.getHomeNews();
        model.addAttribute("news", data);
        return "/dashboard/news/index";
    }

    @GetMapping("/dashboard/news/create")
    public String addNews(Model model) {
        List<NewsHomeDto> news = newsService.getHomeNews();
        model.addAttribute("news", news);
        return "/dashboard/news/create";
    }

    @PostMapping("/dashboard/news/create")
    public String create(NewsCreateDto newsCreateDto, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam("image") MultipartFile file) throws IOException {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        newsCreateDto.setPhotoUrl(rand + fileNames.toString());
        newsService.createNews(newsCreateDto);
        return "redirect:/dashboard/news";
    }

    @GetMapping("/dashboard/news/delete/{id}")
    public String deleteNews(@PathVariable Long id) {
        return "/dashboard/news/delete";
    }

    @PostMapping("/dashboard/news/delete/{id}")
    public String removeNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return "redirect:/dashboard/news";
    }

    @GetMapping("/dashboard/news/update/{id}")
    public String updateNews(@PathVariable Long id, Model model) {
        NewsUpdateDto news = newsService.findUpdateNews(id);
        model.addAttribute("news", news);
        return "/dashboard/news/update";
    }

    @PostMapping("/dashboard/news/update/{id}")
    public String updateNews(@PathVariable Long id, NewsUpdateDto  newsUpdateDto, @RequestParam("image") MultipartFile file) throws IOException {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        newsUpdateDto.setPhotoUrl(rand + fileNames.toString());
        newsService.updateNews(newsUpdateDto, id);
        return "redirect:/dashboard/news";
    }
}

