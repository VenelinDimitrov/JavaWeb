package com.example.coffeeshop.web;

import com.example.coffeeshop.model.Service.AddOrderServiceModel;
import com.example.coffeeshop.model.binding.AddOrderBindingModel;
import com.example.coffeeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrderConfirm(@Valid AddOrderBindingModel addOrderBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addOrderBindingModel", addOrderBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderBindingModel", bindingResult);

            return "redirect:add";
        }

        orderService.addOrder(modelMapper.map(addOrderBindingModel, AddOrderServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/ready/{id}")
    public String readyOrder(@PathVariable Long id){
        orderService.completeOrder(id);

        return "redirect:/";
    }

    @ModelAttribute
    public AddOrderBindingModel addOrderBindingModel() {
        return new AddOrderBindingModel();
    }
}
