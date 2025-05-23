package org.example.simpletwitter.controller;

import org.example.simpletwitter.model.User;
import org.example.simpletwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model, User userDto){
        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, User userDto){
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") User userDto, Model model){
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null){
            model.addAttribute("User exist!", user);
            return "register";
        }
        userService.save(userDto);
        return "redirect:/register?success";
    }
}
