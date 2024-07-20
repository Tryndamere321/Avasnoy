package com.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantity;
    private Long productId;
    private Long userId;
    private Double totalPrice;

    @OneToMany(mappedBy = "cart")
    private List<Product> products = new ArrayList<>();

}
