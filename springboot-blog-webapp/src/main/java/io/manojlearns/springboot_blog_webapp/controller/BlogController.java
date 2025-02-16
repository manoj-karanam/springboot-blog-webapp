package io.manojlearns.springboot_blog_webapp.controller;


import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import io.manojlearns.springboot_blog_webapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class BlogController {

    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public String blogPosts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("postsResponse", posts);
        return "blog/view_posts";
    }
}
