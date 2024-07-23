package com.example.dtos.NewsDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;


@Data
public class NewsCreateDto {
    @NotBlank(message = "title is mandatory")
    private String title;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotBlank(message = "photoUrl is mandatory")
    private String photoUrl;
    @NotBlank(message = "date is mandatory")
    private LocalDate date;
}
