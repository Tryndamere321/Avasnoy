package com.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String address;
    private String city;
    private String productName;
    private Double totalPrice;
    private Double productPrice;
    private Double deliveryPrice;
    @ManyToOne
    private Product product;
}
