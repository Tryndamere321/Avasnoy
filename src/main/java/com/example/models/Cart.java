package com.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantity;
    private Long userId;
    private Double totalPrice;
    private Boolean isOrder=false;

    @ManyToOne
    private Product product;
    @ManyToMany(mappedBy = "cart")
    private List<Order> orders = new ArrayList<>();


}
