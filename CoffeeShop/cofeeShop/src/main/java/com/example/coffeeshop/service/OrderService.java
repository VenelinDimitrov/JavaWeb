package com.example.coffeeshop.service;

import com.example.coffeeshop.model.Service.AddOrderServiceModel;
import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    AddOrderServiceModel addOrder(AddOrderServiceModel addOrderServiceModel);

    List<OrderViewModel> getAllOrdersByPriceDesc();

    void completeOrder(Long id);
}
