package com.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
    private LocalDate date;
    @OneToMany(mappedBy = "news")
    private List<Comment> comments = new ArrayList<>();
}
