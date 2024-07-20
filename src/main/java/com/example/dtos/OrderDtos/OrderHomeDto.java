package com.example.dtos.OrderDtos;

import com.example.dtos.ProductDtos.ProductHomeDto;
import lombok.Data;

import java.util.Date;

@Data
public class OrderHomeDto {
    private Long id;
    private Date date;
    private String address;
    private String city;
    private String productName;
    private Double totalPrice;
    private Double productPrice;
    private Double deliveryPrice;
    private ProductHomeDto product;
}
