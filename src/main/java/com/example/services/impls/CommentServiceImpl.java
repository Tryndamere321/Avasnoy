package com.example.services.impls;


import com.example.dtos.CommentDtos.CommentCreateDto;
import com.example.dtos.CommentDtos.CommentDto;
import com.example.models.Comment;
import com.example.models.News;
import com.example.models.UserEntity;
import com.example.repostories.CommentRepository;
import com.example.repostories.UserRepository;
import com.example.services.CommentService;
import com.example.services.NewsService;
import com.example.services.ProductService;
import com.example.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsService newsService;

    @Override
    public void addComment(CommentCreateDto commentCreate, String email) {
        UserEntity user = userService.findByEmail(email);
        News news=newsService.get(commentCreate.getNewsId());
        Comment newComment = modelMapper.map(commentCreate, Comment.class);
        newComment.setUser(user);
        commentRepository.save(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByNewsId(Long newsId) {
        List<Comment> result = commentRepository.findByNewsId(newsId);
        List<CommentDto> comments = result.stream().map(c->modelMapper.map(c,CommentDto.class)).collect(Collectors.toList());
        return comments;
    }


}
