package com.example.services.impls;

import com.example.dtos.CartDtos.CartRequestDto;
import com.example.dtos.OrderDtos.OrderAddDto;
import com.example.models.Cart;
import com.example.models.Order;
import com.example.models.UserEntity;
import com.example.repostories.CartRepository;
import com.example.repostories.OrderRepository;
import com.example.repostories.UserRepository;
import com.example.services.OrderService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Override
    public Order createOrder(OrderAddDto orderAddDto,String email) {
        UserEntity userEntity = userService.findByEmail(email);
        Order order = new Order();
        order.setUser(userEntity);
        order.setAddress(orderAddDto.getAddress());
        order.setTotalPrice(orderAddDto.getTotalPrice());
        List<Cart> carts = new ArrayList<>();
        Integer number=orderAddDto.getCartIds().size();
        for (int i=0;i<number;i++) {
            Cart cart=cartRepository.findById(orderAddDto.getCartIds().get(i)).orElseThrow();
            carts.add(cart);
        }
        order.setCart(carts);
        orderRepository.save(order);
        return order;
    }

}
