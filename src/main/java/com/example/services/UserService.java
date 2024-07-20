package com.example.services;

import com.example.dtos.UserDtos.UserRegisterDto;
import com.example.models.UserEntity;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    UserEntity findByEmail(String email);
    UserEntity findByUserId(Long id);
}
