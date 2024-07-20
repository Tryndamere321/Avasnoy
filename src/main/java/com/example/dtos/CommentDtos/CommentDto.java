package com.example.dtos.CommentDtos;

import com.example.dtos.UserDtos.UserRegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long newsId;
    private String content;
    private UserRegisterDto user;
}
