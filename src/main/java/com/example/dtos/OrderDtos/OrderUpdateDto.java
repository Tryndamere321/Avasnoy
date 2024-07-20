package com.example.dtos.OrderDtos;

import lombok.Data;

import java.util.Date;

@Data
public class OrderUpdateDto {
    private Long id;
    private Date date;
    private String address;
    private String city;
    private String productName;
    private Double totalPrice;
    private Double productPrice;
    private Double deliveryPrice;
    private Long productId;
}
