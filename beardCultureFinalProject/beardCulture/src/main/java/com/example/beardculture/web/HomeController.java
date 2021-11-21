package com.example.beardculture.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contact-us";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy-policy";
    }
}
