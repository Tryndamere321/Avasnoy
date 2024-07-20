package com.example.services.impls;

import com.example.dtos.CategoryDtos.CategoryDto;
import com.example.dtos.CategoryDtos.CategoryHomeDto;
import com.example.dtos.CategoryDtos.CategoryUpdateDto;
import com.example.dtos.NewsDtos.NewsCreateDto;
import com.example.dtos.NewsDtos.NewsHomeDto;
import com.example.dtos.NewsDtos.NewsUpdateDto;
import com.example.dtos.ProductDtos.ProductDetailDto;
import com.example.dtos.ProductDtos.ProductHomeDto;
import com.example.exception.NotFoundExeption;
import com.example.models.Category;
import com.example.models.News;
import com.example.models.Product;
import com.example.payloads.ArticlePagination;
import com.example.payloads.NewsPagination;
import com.example.repostories.NewsRepository;
import com.example.services.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public void createNews(NewsCreateDto newsCreateDto) {
        News  news = new News();
        news.setTitle(newsCreateDto.getTitle());
        news.setDescription(newsCreateDto.getDescription());
        news.setPhotoUrl(newsCreateDto.getPhotoUrl());
        news.setDate(newsCreateDto.getDate());
        newsRepository.save(news);
    }

    @Override
    public List<NewsHomeDto> getHomeNews() {
        List<NewsHomeDto> news = newsRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x, NewsHomeDto.class)).toList();

        return news;
    }

    @Override
    public void updateNews(NewsUpdateDto newsUpdateDto, Long id) {
        News findNews=newsRepository.findById(id).orElseThrow(NotFoundExeption::new);
        findNews.setTitle(newsUpdateDto.getTitle());
        findNews.setDescription(newsUpdateDto.getDescription());
        findNews.setPhotoUrl(newsUpdateDto.getPhotoUrl());
        findNews.setDate(newsUpdateDto.getDate());
        newsRepository.save(findNews);
    }

    @Override
    public void deleteNews(Long id) {
        News news =newsRepository.findById(id).orElseThrow(NotFoundExeption::new);
        newsRepository.deleteById(id);
    }

    @Override
    public NewsUpdateDto findUpdateNews(Long id) {
        News findNews = newsRepository.findById(id).orElseThrow(NotFoundExeption::new);
        NewsUpdateDto result = modelMapper.map(findNews, NewsUpdateDto.class);
        return result;
    }

    @Override
    public News findNewsById(Long id) {
        return newsRepository.findById(id).orElseThrow(NotFoundExeption::new);
    }

    @Override
    public List<NewsHomeDto> getNews() {
        List<News> news = newsRepository.findAll();
        List<NewsHomeDto> result = news
                .stream()
                .map(x -> modelMapper.map(x, NewsHomeDto.class))
                .toList();
        return result;
    }
    @Override
    public NewsPagination getHomePaginationNews(Integer currentPage) {
        currentPage = currentPage == null ? 1 : currentPage;
        Long articleTotalCount = newsRepository.count();
        int articlesPerPage = 5;
        int totalPageCount = (int) Math.ceil((double) articleTotalCount / articlesPerPage);

        currentPage = Math.min(currentPage, totalPageCount);  // Ensure current page does not exceed total pages

        int skip = (currentPage - 1) * articlesPerPage;
        List<News> articles = newsRepository.findAll().stream().skip(skip).limit(articlesPerPage).collect(Collectors.toList());
        List<NewsHomeDto> results = articles.stream().map(article -> modelMapper.map(article, NewsHomeDto.class)).collect(Collectors.toList());

        NewsPagination pagination = new NewsPagination(results, (long) totalPageCount);
        return pagination;
    }
    @Override
    public NewsHomeDto getDetail(Long id) {
        News news = newsRepository.findById(id).orElseThrow(NotFoundExeption::new);
        NewsHomeDto result = modelMapper.map(news, NewsHomeDto.class);
        return result;
    }
    @Override
    public News get(Long id) {
        News news=newsRepository.findById(id).orElseThrow();
        return news;
    }

}
