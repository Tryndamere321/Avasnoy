package com.example.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Double totalPrice;
    @ManyToMany
    @JoinTable(
            name="orders_carts",
            joinColumns = {@JoinColumn(name="order_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="cart_id",referencedColumnName = "id")}
    )
    private List<Cart> cart;
    @ManyToOne
    private UserEntity user;
}
