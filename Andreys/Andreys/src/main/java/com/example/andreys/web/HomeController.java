package com.example.andreys.web;

import com.example.andreys.model.entity.Product;
import com.example.andreys.sec.CurrentUser;
import com.example.andreys.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class HomeController {

    private CurrentUser currentUser;
    private ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping()
    public String index(Model model) {
        if (currentUser.getId() == null){
            return "index";
        }

        Set<Product> allProducts = productService.getAllProducts();
        model.addAttribute("allProducts", allProducts);

        return "home";
    }
}
