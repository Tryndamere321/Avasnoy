package com.example.dtos.NewsDtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class NewsHomeDto {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
    private LocalDate date;
}
