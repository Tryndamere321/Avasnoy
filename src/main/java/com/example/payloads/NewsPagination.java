package com.example.payloads;

import com.example.dtos.NewsDtos.NewsHomeDto;
import com.example.dtos.ProductDtos.ProductHomeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsPagination {
    private List<NewsHomeDto> articles;
    private Long pageSize;
}
