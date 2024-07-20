package com.example.services;


import com.example.dtos.CommentDtos.CommentCreateDto;
import com.example.dtos.CommentDtos.CommentDto;

import java.util.List;

public interface CommentService {
    void addComment(CommentCreateDto commentCreate, String email);
    List<CommentDto> getCommentsByNewsId(long newsId);



}
