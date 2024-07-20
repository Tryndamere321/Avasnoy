package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private Double price;
    private Double weight;
    private String photoUrl;
    private String seoUrl;

    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();
    @ManyToOne
    private Cart cart;
}
