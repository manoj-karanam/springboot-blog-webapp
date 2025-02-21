package io.manojlearns.springboot_blog_webapp.controller;

import io.manojlearns.springboot_blog_webapp.dto.RegistrationDto;
import io.manojlearns.springboot_blog_webapp.entity.User;
import io.manojlearns.springboot_blog_webapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.BindParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {


    private UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        //this object holds form data
        RegistrationDto user=new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    //handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result, Model model){
        User existingUser= userService.findByEmail(user.getEmail());
        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email",null,"There is already a user with same Email");
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
