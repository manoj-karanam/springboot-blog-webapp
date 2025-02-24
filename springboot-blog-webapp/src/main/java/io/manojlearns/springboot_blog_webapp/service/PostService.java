package io.manojlearns.springboot_blog_webapp.service;

import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import jakarta.validation.Valid;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPosts();

    List<PostDto> findPostsByUser();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void deletePost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);


    void updatePost(@Valid PostDto post);
}
