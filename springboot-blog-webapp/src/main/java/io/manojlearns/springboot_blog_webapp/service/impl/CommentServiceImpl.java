package io.manojlearns.springboot_blog_webapp.service.impl;

import io.manojlearns.springboot_blog_webapp.dto.CommentDto;
import io.manojlearns.springboot_blog_webapp.entity.Comment;
import io.manojlearns.springboot_blog_webapp.entity.Post;
import io.manojlearns.springboot_blog_webapp.entity.User;
import io.manojlearns.springboot_blog_webapp.mapper.CommentMapper;
import io.manojlearns.springboot_blog_webapp.repository.CommentRepository;
import io.manojlearns.springboot_blog_webapp.repository.PostRepository;
import io.manojlearns.springboot_blog_webapp.repository.UserRepository;
import io.manojlearns.springboot_blog_webapp.service.CommentService;
import io.manojlearns.springboot_blog_webapp.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(PostRepository postRepository,
                              CommentRepository commentRepository,
                              UserRepository userRepository) {

        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository=userRepository;
    }


    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment= CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments=commentRepository.findAll();
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId=createdBy.getId();
        List<Comment> comments =  commentRepository.findCommentsByPost(userId);
        return comments.stream()
                .map((comment)->CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());

    }
}
