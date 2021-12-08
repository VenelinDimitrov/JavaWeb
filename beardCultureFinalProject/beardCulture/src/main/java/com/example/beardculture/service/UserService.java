package com.example.beardculture.service;

import com.example.beardculture.model.entity.User;
import com.example.beardculture.model.service.UserDetailsUpdateServiceModel;
import com.example.beardculture.model.service.UserRegisterServiceModel;

import java.util.List;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel);

    User getUserByUsername(String name);

    User getUserById(Long id);

    void updateUserDetails(UserDetailsUpdateServiceModel userDetailsUpdateServiceModel);

    void removeProductFromBox(Long id, Long productId);

    User getUserByEmail(String email);

    void saveUser(User user);

    List<User> getAllUsers();
}
