package com.example.dtos.UserDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    @NotBlank(message = "Name is mandatory")
    private String firstName;
    @NotBlank(message = "Name is mandatory")
    private String lastName;
    @NotBlank(message = "Name is mandatory")
    private String email;
    @NotBlank(message = "Name is mandatory")
    private String password;
}