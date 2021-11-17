package com.example.musicdb.service;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.service.UserServiceModel;

import java.util.Set;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logoutUser();

}
