package com.example.beardculture.web;

import com.example.beardculture.model.binding.AddProductBindingModel;
import com.example.beardculture.service.ProductService;
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

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
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

    @DeleteMapping("/delete/{id}")
    public String removeProductFromSubscription(@PathVariable Long id){

        // TODO implement Remove from box button
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
