package io.manojlearns.springboot_blog_webapp.mapper;

import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import io.manojlearns.springboot_blog_webapp.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {

    //map - Post entity to PostDto

    public static PostDto maptoPostDto(Post post){

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .comments(post.getComments().stream()
                        .map((comment)->CommentMapper.mapToCommentDto(comment))
                        .collect(Collectors.toSet()))
                .build();
    }

    //map PostDto to post entity
    public static Post maptoPost(PostDto postDto){

        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();
    }
}
