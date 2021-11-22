package com.example.beardculture.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @GetMapping("/oils")
    public String oilsPage(){
        return "oils";
    }

    @GetMapping("/balms")
    public String balmsPage(){
        return "balms";
    }

    @GetMapping("/gear")
    public String gearPage(){
        return "gear";
    }

    @GetMapping("/add")
    public String addProduct(){
        return "add-product";
    }

    @GetMapping("/details")
    public String productDetails(){
        return "product-details";
    }
}
