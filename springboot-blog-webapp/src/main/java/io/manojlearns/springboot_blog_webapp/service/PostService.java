package io.manojlearns.springboot_blog_webapp.service;

import io.manojlearns.springboot_blog_webapp.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void deletePost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);
}
