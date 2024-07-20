package com.example.repostories;

import com.example.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
 List<Product> findByCategoryId(Long id);
 List<Product> findProductById(Long id);
}
