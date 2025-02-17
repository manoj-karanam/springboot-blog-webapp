package io.manojlearns.springboot_blog_webapp.controller;

import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import io.manojlearns.springboot_blog_webapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import io.manojlearns.springboot_blog_webapp.dto.CommentDto;
import io.manojlearns.springboot_blog_webapp.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService= postService;

    }

    //handler method to create form submit request
    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                @Valid @ModelAttribute("comment") CommentDto commentDto,
                                BindingResult result, Model model){

        PostDto postDto = postService.findPostByUrl(postUrl);
        if(result.hasErrors()){
            model.addAttribute("comment", commentDto);
            model.addAttribute("post", postDto);
            return "blog/blog_post";
        }
        commentService.createComment(postUrl, commentDto);
        return "redirect:/post/"+postUrl;
    }

}
