package com.example.services;

import com.example.dtos.CartDtos.CartCreateDto;
import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.CategoryDtos.CategoryUpdateDto;
import com.example.models.Cart;

import java.util.List;

public interface CartService {
 void addCart(CartCreateDto cartCreateDto,String email);
 List<CartRequestDto> getProducts();
 Double calculateTotalPrice(CartRequestDto cartRequestDto);
 public void deleteProduct(Long id);
 void updateCart(CartRequestDto cartUpdate, Long id);
}
