package com.example.services;

import com.example.dtos.UserDtos.UserRegisterDto;
import com.example.models.UserEntity;

public interface UserService {
    public boolean confirmEmail(String email, String token);
    void registerUser(UserRegisterDto userRegisterDto);
    UserEntity findByEmail(String email);
    UserEntity findByUserId(Long id);
}
