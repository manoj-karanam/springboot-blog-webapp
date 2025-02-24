package io.manojlearns.springboot_blog_webapp.service.impl;

import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import io.manojlearns.springboot_blog_webapp.entity.Post;
import io.manojlearns.springboot_blog_webapp.entity.User;
import io.manojlearns.springboot_blog_webapp.mapper.PostMapper;
import io.manojlearns.springboot_blog_webapp.repository.PostRepository;
import io.manojlearns.springboot_blog_webapp.repository.UserRepository;
import io.manojlearns.springboot_blog_webapp.service.PostService;
import io.manojlearns.springboot_blog_webapp.util.SecurityUtils;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository ){
        this.postRepository = postRepository;
        this.userRepository= userRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map((post) -> PostMapper.maptoPostDto(post))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Post> posts = postRepository.findPostByUser(userId);
        return posts.stream()
                .map((post)-> PostMapper.maptoPostDto(post))
                .collect(Collectors.toList());

    }

    @Override
    public void createPost(PostDto postDto) {
        String email= SecurityUtils.getCurrentUser().getUsername();
        User user= userRepository.findByEmail(email);
        Post post = PostMapper.maptoPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }



    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.maptoPostDto(post);
    }

    public void updatePost(PostDto postDto){
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Post post = PostMapper.maptoPost(postDto);
        post.setCreatedBy(createdBy);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findPostByUrl(postUrl).get();
        return PostMapper.maptoPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream().map(PostMapper::maptoPostDto).collect(Collectors.toList());
    }



}
