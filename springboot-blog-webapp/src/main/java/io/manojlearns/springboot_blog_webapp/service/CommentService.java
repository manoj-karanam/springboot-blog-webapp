package io.manojlearns.springboot_blog_webapp.service;

import io.manojlearns.springboot_blog_webapp.dto.CommentDto;

public interface CommentService {

    void createComment(String postUrl, CommentDto commentDto);
}
