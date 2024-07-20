package com.example.controllers;

import com.example.dtos.UserDtos.UserRegisterDto;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }


    @GetMapping("/register")
    public String register(){
        return "/auth/register";
    }

    @PostMapping("/register")
    public String register(UserRegisterDto userRegisterDto){
        userService.registerUser(userRegisterDto);
        return "redirect:/login";
    }
}

