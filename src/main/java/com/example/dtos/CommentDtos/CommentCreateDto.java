package com.example.dtos.CommentDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto {
    @NotBlank(message = "Name is mandatory")
    private Long newsId;
    @NotBlank(message = "Name is mandatory")
    private String content;
}
