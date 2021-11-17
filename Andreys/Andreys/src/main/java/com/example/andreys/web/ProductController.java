package com.example.andreys.web;

import com.example.andreys.model.binding.AddProductBindingModel;
import com.example.andreys.model.entity.Product;
import com.example.andreys.model.service.AddProductServiceModel;
import com.example.andreys.model.service.ProductDetailsServiceModel;
import com.example.andreys.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "add-product";
    }

    @PostMapping("/add")
    public String addConfirm(AddProductBindingModel addProductBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductBindingModel", addProductBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel", bindingResult);

            return "redirect:add";
        }

        productService.addProduct(modelMapper.map(addProductBindingModel, AddProductServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/details/{name}")
    public String details(@PathVariable String name, Model model) {
        Product product = productService.findProductByName(name);

        model.addAttribute("productDetailsServiceModel", modelMapper.map(product, ProductDetailsServiceModel.class));

        return "details-product";
    }

    @GetMapping("/delete/{name}")
    public String deleteProduct(@PathVariable String name) {
        productService.deleteProductByName(name);

        return "redirect:/";
    }

    @ModelAttribute
    public AddProductBindingModel addProductBindingModel() {
        return new AddProductBindingModel();
    }

    @ModelAttribute
    public ProductDetailsServiceModel productDetailsServiceModel() {
        return new ProductDetailsServiceModel();
    }
}
