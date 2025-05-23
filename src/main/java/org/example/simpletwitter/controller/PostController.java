package org.example.simpletwitter.controller;

import org.example.simpletwitter.config.CustomUserDetails;
import org.example.simpletwitter.model.Post;
import org.example.simpletwitter.service.PostService;
import org.example.simpletwitter.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/add")
    public String showAddPost(Model model){
        model.addAttribute("posts", new Post());
        return "add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        var existingUser = userService.findByUsername(user.getUsername());
        post.setUser(existingUser);
        postService.save(post);
        return "redirect:/";
    }
}
