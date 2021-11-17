package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.Service.AddOrderServiceModel;
import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.sec.CurrentUser;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final UserService userService;
    private CurrentUser currentUser;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CategoryService categoryService, UserService userService, CurrentUser currentUser) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public AddOrderServiceModel addOrder(AddOrderServiceModel addOrderServiceModel) {
        Order order = modelMapper.map(addOrderServiceModel, Order.class);
        order.setEmployee(userService.findById(currentUser.getId()));
        order.setCategory(categoryService.findByName(addOrderServiceModel.getCategory().getName()));

        orderRepository.save(order);

        return modelMapper.map(order, AddOrderServiceModel.class);
    }

    @Override
    public List<OrderViewModel> getAllOrdersByPriceDesc() {

        return orderRepository.findAllByOrderByPriceDesc().stream().map(order -> modelMapper.map(order, OrderViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void completeOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
