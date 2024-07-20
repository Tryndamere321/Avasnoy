package com.example.services.impls;

import com.example.dtos.ProductDtos.*;
import com.example.exception.NotFoundExeption;
import com.example.models.Product;
import com.example.models.Category;
import com.example.payloads.ArticlePagination;
import com.example.repostories.ProductRepository;
import com.example.services.ProductService;
import com.example.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository articleRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void CreateArticle(ProductAddDto articleAddDto) {
        Category findCategory= categoryService.findCategoryById(articleAddDto.getCategoryId());
        Product article = new Product();
        article.setPrice(articleAddDto.getPrice());
        article.setProduct(articleAddDto.getProduct());
        article.setWeight(articleAddDto.getWeight());
        article.setPhotoUrl(articleAddDto.getPhotoUrl());
        article.setSeoUrl(" ");
        article.setCategory(findCategory);
        articleRepository.save(article);
    }



    @Override
    public List<ProductDashboardDto> getDashboardArticles() {
        List<Product> articles = articleRepository.findAll();
        List<ProductDashboardDto> result = articles.stream().map(article -> modelMapper
                        .map(article, ProductDashboardDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public ProductUpdateDto getUpdateArticles(Long id) {
        Product findArticle=articleRepository.findById(id).orElseThrow(NotFoundExeption::new);
        ProductUpdateDto article =modelMapper.map(findArticle, ProductUpdateDto.class);
        return article;
    }

    @Override
    public void updateArticle(ProductUpdateDto articleUpdateDto, Long id) {
        Product findArticle= articleRepository.findById(id).orElseThrow(NotFoundExeption::new);
        Category findCategory = categoryService.findCategoryById(articleUpdateDto.getCategoryId());
        findArticle.setCategory(findCategory);
        findArticle.setPrice(articleUpdateDto.getPrice());
        findArticle.setProduct(articleUpdateDto.getProduct());
        findArticle.setWeight(articleUpdateDto.getWeight());
        findArticle.setPhotoUrl(articleUpdateDto.getPhotoUrl());
        findArticle.setSeoUrl(" ");
        articleRepository.save(findArticle);
    }

    @Override
    public ProductUpdateDto findUpdateArticle(Long id) {
        Product findArticle = articleRepository.findById(id).orElseThrow(NotFoundExeption::new);
        ProductUpdateDto result = modelMapper.map(findArticle, ProductUpdateDto.class);
        return result;
    }

    @Override
    public void removeArticle(Long id) {
        Product article = articleRepository.findById(id).orElseThrow(NotFoundExeption::new);
        articleRepository.deleteById(id);
    }

    @Override
    public List<ProductHomeDto> getHomeArticle() {
        List<Product> articles = articleRepository.findAll();
        List<ProductHomeDto> result = articles.stream().map(article -> modelMapper
                        .map(article, ProductHomeDto.class))
                .collect(Collectors.toList());
        return result;
    }
    @Override
     public List<ProductHomeDto> get5Articles(){
        List<Product> articles = articleRepository.findAll();
        Collections.shuffle(articles); // Randomize the list
        List<ProductHomeDto> result = articles.stream()
                .limit(5) // Limit to 5 articles
                .map(article -> modelMapper.map(article, ProductHomeDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public ProductDetailDto getDetail(Long id) {
        Product article = articleRepository.findById(id).orElseThrow(NotFoundExeption::new);
        ProductDetailDto result = modelMapper.map(article, ProductDetailDto.class);
        return result;
    }

    @Override
    public Product get(Long id) {
        Product article=articleRepository.findById(id).orElseThrow(NotFoundExeption::new);
        return article;
    }

    @Override
    public ProductDto sell(Long id) {
        Product article=articleRepository.findById(id).orElseThrow(NotFoundExeption::new);
        ProductDto result = modelMapper.map(article, ProductDto.class);
        return result;
    }

    @Override
    public List<ProductDto> getFruits(Long id) {
        List<Product> products=productRepository.findByCategoryId(7L);
        List<ProductDto> result= products.stream().map(product-> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<ProductDto> getVegetables(Long id) {
        List<Product> products=productRepository.findByCategoryId(8L);
        List<ProductDto> result= products.stream().map(product-> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());

        return result;
    }
    @Override
    public ArticlePagination getHomePaginationArticle(Integer currentPage) {
        currentPage = currentPage == null ? 1 : currentPage;
        Long articleTotalCount = articleRepository.count();
        int articlesPerPage = 5;
        int totalPageCount = (int) Math.ceil((double) articleTotalCount / articlesPerPage);

        currentPage = Math.min(currentPage, totalPageCount);  // Ensure current page does not exceed total pages

        int skip = (currentPage - 1) * articlesPerPage;
        List<Product> articles = articleRepository.findAll().stream().skip(skip).limit(articlesPerPage).collect(Collectors.toList());
        List<ProductHomeDto> results = articles.stream().map(article -> modelMapper.map(article, ProductHomeDto.class)).collect(Collectors.toList());

        ArticlePagination pagination = new ArticlePagination(results, (long) totalPageCount);
        return pagination;
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


}
