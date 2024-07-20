package com.example.services;

import com.example.dtos.CategoryDtos.CategoryAddDto;
import com.example.dtos.CategoryDtos.CategoryDto;
import com.example.dtos.CategoryDtos.CategoryHomeDto;
import com.example.dtos.CategoryDtos.CategoryUpdateDto;
import com.example.dtos.NewsDtos.NewsCreateDto;
import com.example.dtos.NewsDtos.NewsHomeDto;
import com.example.dtos.NewsDtos.NewsUpdateDto;
import com.example.models.Category;
import com.example.models.News;
import com.example.payloads.NewsPagination;

import java.util.List;

public interface NewsService {
    void createNews(NewsCreateDto newsCreateDto);
    List<NewsHomeDto> getHomeNews();
    void updateNews(NewsUpdateDto newsUpdateDto, Long id);
    public void deleteNews(Long id);
    NewsUpdateDto findUpdateNews(Long id);
    public News findNewsById(Long id);
    List<NewsHomeDto> getNews();
    NewsPagination getHomePaginationNews(Integer currentPage);
    public NewsHomeDto getDetail(Long id);
    public News get(Long id);
}
