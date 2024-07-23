package com.example.dtos.OrderDtos;

import com.example.dtos.CartDtos.CartDto;
import com.example.dtos.CartDtos.CartRequestDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderAddDto {
    private String address;
    private CartRequestDto cart;
    private List<Long> cartIds;
    private Double totalPrice;

}
