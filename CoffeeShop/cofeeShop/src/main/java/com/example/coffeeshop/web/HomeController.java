package com.example.coffeeshop.web;

import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.model.view.UserViewModel;
import com.example.coffeeshop.sec.CurrentUser;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private CurrentUser currentUser;
    private final UserService userService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public HomeController(CurrentUser currentUser, UserService userService, OrderService orderService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String index(Model model){

        if (currentUser.getId() == null){
            return "index";
        }

        List<OrderViewModel> allOrders = orderService.getAllOrdersByPriceDesc();
        List<UserViewModel> allUsers = userService.getAllUsersByOrderCountDesc();

        int neededTimeForAllOrders = allOrders.stream().map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                .reduce(Integer::sum).orElse(0);

        model.addAttribute("allOrders", allOrders);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("neededTimeForAllOrders", neededTimeForAllOrders);

        return "home";
    }
}
