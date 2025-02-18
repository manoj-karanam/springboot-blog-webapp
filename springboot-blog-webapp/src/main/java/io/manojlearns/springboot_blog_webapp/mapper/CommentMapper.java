package io.manojlearns.springboot_blog_webapp.mapper;

import io.manojlearns.springboot_blog_webapp.dto.CommentDto;
import io.manojlearns.springboot_blog_webapp.entity.Comment;

public class CommentMapper {

    //convert Comment Entity to CommentDto

    public static CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn()).build();
        return commentDto;
    }

    //convert CommentDto Entity to Comment
    public static Comment mapToComment(CommentDto commentDto){
        Comment comment=Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn()).build();
        return comment;
        }
}
