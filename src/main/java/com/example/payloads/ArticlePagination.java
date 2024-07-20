package com.example.payloads;

import com.example.dtos.ProductDtos.ProductHomeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePagination {
    private List<ProductHomeDto> articles;
    private Long pageSize;
}
