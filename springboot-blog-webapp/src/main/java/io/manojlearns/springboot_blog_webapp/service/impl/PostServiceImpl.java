package io.manojlearns.springboot_blog_webapp.service.impl;

import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import io.manojlearns.springboot_blog_webapp.entity.Post;
import io.manojlearns.springboot_blog_webapp.mapper.PostMapper;
import io.manojlearns.springboot_blog_webapp.repository.PostRepository;
import io.manojlearns.springboot_blog_webapp.service.PostService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository ){
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map((post) -> PostMapper.maptoPostDto(post))
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.maptoPost(postDto);
        postRepository.save(post);
    }
}
