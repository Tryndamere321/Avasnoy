package com.example.dtos.NewsDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class NewsUpdateDto {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String title;
    @NotBlank(message = "Name is mandatory")
    private String description;
    @NotBlank(message = "Name is mandatory")
    private String photoUrl;
    @NotBlank(message = "Name is mandatory")
    private LocalDate date;
}
