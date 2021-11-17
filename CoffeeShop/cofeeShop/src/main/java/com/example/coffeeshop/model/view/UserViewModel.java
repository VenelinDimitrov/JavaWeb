package com.example.coffeeshop.model.view;

import com.example.coffeeshop.model.entity.Order;

import java.util.Set;

public class UserViewModel {

    private String username;
    private Integer numberOfOrders;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
