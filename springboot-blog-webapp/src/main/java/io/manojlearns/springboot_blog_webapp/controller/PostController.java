package io.manojlearns.springboot_blog_webapp.controller;

import io.manojlearns.springboot_blog_webapp.dto.CommentDto;
import io.manojlearns.springboot_blog_webapp.dto.PostDto;
import io.manojlearns.springboot_blog_webapp.service.CommentService;
import io.manojlearns.springboot_blog_webapp.service.PostService;
import io.manojlearns.springboot_blog_webapp.util.ROLE;
import io.manojlearns.springboot_blog_webapp.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService){
        this.postService=postService;
        this.commentService=commentService;
    }

    //create handler method, GET request and return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model){
        String role = SecurityUtils.getRole();
        List<PostDto> posts = null;
        if(ROLE.ROLE_ADMIN.name().equals(role)){
            posts= postService.findAllPosts();
        }
        else{
            posts = postService.findPostsByUser();
        }
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

    // handler method to handle edit post form submit request
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("post") PostDto post,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post", post);
            return "admin/edit_post";
        }

        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
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

    //handler method to display the comments
    @GetMapping("admin/posts/comments")
    public String postComments(Model model){
        String role = SecurityUtils.getRole();
        List<CommentDto> comments = null;
        if(ROLE.ROLE_ADMIN.name().equals(role)){
            comments = commentService.findAllComments();
        }
        else{
            comments = commentService.findCommentByPost();
        }
        model.addAttribute("comments", comments);
        return "admin/comments";
    }

    //handler method to delete a comment
    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";
    }


}
