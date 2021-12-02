package com.example.beardculture.service;

import com.example.beardculture.model.entity.User;
import com.example.beardculture.model.service.UserDetailsUpdateServiceModel;
import com.example.beardculture.model.service.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel);

    User getUserByUsername(String name);

    void updateUserDetails(UserDetailsUpdateServiceModel userDetailsUpdateServiceModel);
}
