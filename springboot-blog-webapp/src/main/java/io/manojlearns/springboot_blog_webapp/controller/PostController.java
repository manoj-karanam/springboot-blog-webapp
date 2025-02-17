package io.manojlearns.springboot_blog_webapp.controller;

import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import io.manojlearns.springboot_blog_webapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class PostController {

    private PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    //create handler method, GET request and return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);   //key-posts and value-posts
        return "/admin/posts";
    }

    //handler method to handle new post request

    @GetMapping("admin/posts/newpost")
    public String newPostForm(Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "admin/create_post";
    }

    //handler method to handle form submit request
    @PostMapping("admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    private static String getUrl(String postTitle){
        //OOPS Concepts Explained in Java
        //oops-concepts-explained-in-java
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;

    }

    //handler method to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }
    //handler method to handle delete post request
    @GetMapping("admin/posts/{postId}/delete")
    public  String deletePostForm(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    //handler method to handle view post request
    @GetMapping("admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        System.out.println(postDto);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }

    //handler method to handle search blog posts request
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value="query") String query, Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
    }


}
