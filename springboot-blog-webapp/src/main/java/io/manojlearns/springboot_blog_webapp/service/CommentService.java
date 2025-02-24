package io.manojlearns.springboot_blog_webapp.service;

import io.manojlearns.springboot_blog_webapp.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void createComment(String postUrl, CommentDto commentDto);


    List<CommentDto> findAllComments();

    void deleteComment(Long commentId);

    List<CommentDto> findCommentByPost();
}
