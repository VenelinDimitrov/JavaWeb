package com.example.beardculture.web;

import com.example.beardculture.model.binding.AddProductBindingModel;
import com.example.beardculture.service.ProductService;
import com.example.beardculture.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ProductController(ProductService productService, ModelMapper modelMapper, UserService userService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

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

    @PostMapping("/add")
    public String addProductConfirm(@Valid AddProductBindingModel addProductBindingModel,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductBindingModel", addProductBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel", bindingResult);

            return "redirect:add-product";
        }

        productService.addProduct(addProductBindingModel);

        return "redirect:add";
    }

    @GetMapping("/removeProduct/{userId}/{productId}")
    public String removeProductFromSubscription(@PathVariable Long userId, @PathVariable Long productId){
        userService.removeProductFromBox(userId, productId);

        return "redirect:/users/account";
    }

    @GetMapping("/details")
    public String productDetails(){
        return "product-details";
    }


    @ModelAttribute
    public AddProductBindingModel addProductBindingModel(){
        return new AddProductBindingModel();
    }
}
