package com.example.services.impls;

import com.example.dtos.CartDtos.CartCreateDto;
import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.CategoryDtos.CategoryUpdateDto;
import com.example.dtos.ProductDtos.ProductDashboardDto;
import com.example.exception.NotFoundExeption;
import com.example.models.Cart;
import com.example.models.Category;
import com.example.models.Product;
import com.example.models.UserEntity;
import com.example.repostories.CartRepository;
import com.example.repostories.ProductRepository;
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
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addCart(CartCreateDto cartCreateDto, String email) {
        UserEntity userEntity = userService.findByEmail(email);
        // Assuming Cart is identified by user ID and product ID
        List<Cart> existingCart = cartRepository.findByUserIdAndProductId(userEntity.getId(), cartCreateDto.getProductId()).stream().filter(cart -> !cart.getIsOrder()).collect(Collectors.toList());
        if (existingCart.isEmpty()) {
            Cart newCart = new Cart();
            Product product = productRepository.findById(cartCreateDto.getProductId()).orElse(null);
            newCart.setProduct(product);
            newCart.setQuantity(cartCreateDto.getQuantity());
            newCart.setUserId(userEntity.getId());
            assert product != null;
            newCart.setTotalPrice(cartCreateDto.getTotalPrice());
            cartRepository.save(newCart);
        } else {
            for (Cart cart : existingCart) {
            cart.setQuantity(cart.getQuantity() + cartCreateDto.getQuantity());
            cartRepository.save(cart);}
        }
    }

    @Override
    public List<CartRequestDto> getProducts() {
        List<Cart> carts = cartRepository.findAll().stream().filter(cart ->!cart.getIsOrder()).collect(Collectors.toList());
        List<CartRequestDto> result = carts.stream().map(x -> modelMapper
                        .map(x, CartRequestDto.class))
                .collect(Collectors.toList());
        return result;
    }
    @Override
    public Double calculateTotalPrice(CartRequestDto cartRequestDto) {
        Double price = cartRequestDto.getProduct().getPrice();
        Double quantity = cartRequestDto.getQuantity();
        return price * quantity;
    }
    @Override
    public void deleteProduct(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(NotFoundExeption::new);
        cartRepository.deleteById(id);
    }

    @Override
    public void updateCart(CartRequestDto cartUpdate, Long id) {
        Cart findCart=cartRepository.findById(id).orElseThrow(NotFoundExeption::new);
        findCart.setQuantity(cartUpdate.getQuantity());
        cartRepository.save(findCart);
    }

}

