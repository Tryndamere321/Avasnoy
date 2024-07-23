package com.example.repostories;

import com.example.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);
    List<Cart> findByProductId(Long productId);

}
