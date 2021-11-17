package com.example.andreys.service;

import com.example.andreys.model.service.UserLoginServiceModel;
import com.example.andreys.model.service.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel userServiceModel);

    void loginUser(UserLoginServiceModel userLoginServiceModel);

    UserLoginServiceModel findByUsernameAndPassword(String username, String password);

    void logout();
}
