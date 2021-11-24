package com.example.beardculture.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/account")
    public String myAccount() {
        return "my-account";
    }

    @GetMapping("/admin")
    public String adminPanel() {
        return "admin-panel";
    }
}
