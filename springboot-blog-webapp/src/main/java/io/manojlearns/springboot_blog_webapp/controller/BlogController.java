package io.manojlearns.springboot_blog_webapp.controller;


import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import io.manojlearns.springboot_blog_webapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class BlogController {

    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    //handler method to handle http://localhost:8080/
    @RequestMapping("/")
    public String blogPosts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("postsResponse", posts);
        return "blog/view_posts";
    }

    //handler method to handle view post request
    @GetMapping("post/{postUrl}")
    public String showPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto post = postService.findPostByUrl(postUrl);
        model.addAttribute("post", post);
        return "blog/blog_post";
    }
}
