package io.manojlearns.springboot_blog_webapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty(message= "Email Should not be empty or null")
    @Email
    private String email;

    @NotEmpty(message = "Comment field should not be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
