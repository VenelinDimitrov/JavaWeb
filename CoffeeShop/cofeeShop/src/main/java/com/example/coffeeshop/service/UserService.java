package com.example.coffeeshop.service;

import com.example.coffeeshop.model.Service.UserLoginServiceModel;
import com.example.coffeeshop.model.Service.UserRegisterServiceModel;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.view.UserViewModel;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel);

    UserLoginServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(UserLoginServiceModel potentialUser);

    User findById(Long id);

    List<UserViewModel> getAllUsersByOrderCountDesc();
}
