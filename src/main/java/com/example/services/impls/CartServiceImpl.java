package com.example.services.impls;

import com.example.dtos.CartDtos.CartCreateDto;
import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.ProductDtos.ProductDashboardDto;
import com.example.models.Cart;
import com.example.models.Product;
import com.example.models.UserEntity;
import com.example.repostories.CartRepository;
import com.example.repostories.UserRepository;
import com.example.services.CartService;
import com.example.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addCart(CartCreateDto cartCreateDto, String email) {
        UserEntity userEntity = userService.findByEmail(email);
        // Assuming Cart is identified by user ID and product ID
        Optional<Cart> existingCart = cartRepository.findByUserIdAndProductId(userEntity.getId(), cartCreateDto.getProductId());
        if (existingCart.isEmpty()) {
            Cart newCart = new Cart();
            newCart.setProductId(cartCreateDto.getProductId());
            newCart.setQuantity(cartCreateDto.getQuantity());
            newCart.setUserId(userEntity.getId());
            cartRepository.save(newCart);
        } else {
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + cartCreateDto.getQuantity());
            cartRepository.save(cart);
        }
    }

    @Override
    public List<CartRequestDto> getProducts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartRequestDto> result = carts.stream().map(x -> modelMapper
                        .map(x, CartRequestDto.class))
                .collect(Collectors.toList());
        return result;
    }

}

