package io.manojlearns.springboot_blog_webapp.service.impl;

import io.manojlearns.springboot_blog_webapp.dto.CommentDto;
import io.manojlearns.springboot_blog_webapp.entity.Comment;
import io.manojlearns.springboot_blog_webapp.entity.Post;
import io.manojlearns.springboot_blog_webapp.mapper.CommentMapper;
import io.manojlearns.springboot_blog_webapp.repository.CommentRepository;
import io.manojlearns.springboot_blog_webapp.repository.PostRepository;
import io.manojlearns.springboot_blog_webapp.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment= CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

    }
}
