package com.example.dtos.OrderDtos;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.ProductDtos.ProductHomeDto;
import com.example.models.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class OrderHomeDto {
    private Long id;
    private String address;
    private CartRequestDto cart;
    private List<Long> cartIds;
    private UserEntity user;
}
