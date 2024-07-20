package com.example.services;

import com.example.dtos.ProductDtos.*;
import com.example.models.Product;
import com.example.payloads.ArticlePagination;

import java.util.List;

public interface ProductService {
    void CreateArticle(ProductAddDto articleAddDto);

    List<ProductDashboardDto> getDashboardArticles();

    ProductUpdateDto getUpdateArticles(Long id);
    void updateArticle(ProductUpdateDto articleUpdateDto, Long id);

    ProductUpdateDto findUpdateArticle(Long id);

    void removeArticle(Long id);
    List<ProductHomeDto> getHomeArticle();
    ProductDetailDto getDetail(Long id);
    Product get(Long id);
    ProductDto sell(Long id);
    List<ProductDto> getFruits(Long id);
    List<ProductDto> getVegetables(Long id);
    List<ProductHomeDto> get5Articles();
    ArticlePagination getHomePaginationArticle(Integer currentPage);

    Product findProductById(Long productId);
}
