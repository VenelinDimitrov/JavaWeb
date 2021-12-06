package com.example.beardculture.web;

import com.example.beardculture.model.binding.AddProductBindingModel;
import com.example.beardculture.model.binding.ProductUpdateBindingModel;
import com.example.beardculture.model.entity.Product;
import com.example.beardculture.model.service.ProductUpdateServiceModel;
import com.example.beardculture.service.ProductService;
import com.example.beardculture.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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
    public String oilsPage(Model model) {
        List<Product> oils = productService.getAllOils();

        model.addAttribute("oils", oils);

        return "oils";
    }

    @GetMapping("/balms")
    public String balmsPage(Model model) {
        List<Product> balms = productService.getAllBalms();

        model.addAttribute("balms", balms);

        return "balms";
    }

    @GetMapping("/gear")
    public String gearPage(Model model) {
        List<Product> gear = productService.getAllGear();

        model.addAttribute("gear", gear);

        return "gear";
    }

    @GetMapping("/add")
    public String addProduct() {
        return "add-product";
    }

    @PostMapping("/add")
    public String addProductConfirm(@Valid AddProductBindingModel addProductBindingModel,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductBindingModel", addProductBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel", bindingResult);

            return "redirect:add-product";
        }

        productService.addProduct(addProductBindingModel);

        return "redirect:add";
    }

    @GetMapping("/removeProduct/{userId}/{productId}")
    public String removeProductFromSubscription(@PathVariable Long userId, @PathVariable Long productId) {
        userService.removeProductFromBox(userId, productId);

        return "redirect:/users/account";
    }

    @GetMapping("/details/{id}")
    public String productDetails(@PathVariable Long id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("productDetails", product);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        return "product-details";
    }

    @PatchMapping("/edit/{id}")
    public String editProductDetails(@Valid ProductUpdateBindingModel productUpdateBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable Long id) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productUpdateBindingModel", productUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productUpdateBindingModel", bindingResult);

            return "redirect:/products/edit/" + id;
        }

        ProductUpdateServiceModel productUpdateServiceModel = modelMapper.map(productUpdateBindingModel, ProductUpdateServiceModel.class);
        productUpdateServiceModel.setId(id);

        productService.updateProduct(productUpdateServiceModel);

        return "redirect:/products/details/" + id;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView productExceptionHandler(ProductNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("product-not-found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }


    @ModelAttribute
    public AddProductBindingModel addProductBindingModel() {
        return new AddProductBindingModel();
    }
}
