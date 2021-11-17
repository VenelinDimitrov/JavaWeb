package com.example.andreys.service.impl;

import com.example.andreys.model.entity.User;
import com.example.andreys.model.service.UserLoginServiceModel;
import com.example.andreys.model.service.UserRegisterServiceModel;
import com.example.andreys.repository.UserRepository;
import com.example.andreys.sec.CurrentUser;
import com.example.andreys.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserRegisterServiceModel registerUser(UserRegisterServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);

        userRepository.save(user);

        return modelMapper.map(user, UserRegisterServiceModel.class);
    }

    @Override
    public void loginUser(UserLoginServiceModel userLoginServiceModel) {
        currentUser.setId(userLoginServiceModel.getId());
        currentUser.setUsername(userLoginServiceModel.getUsername());
    }

    @Override
    public UserLoginServiceModel findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).orElse(null);

        return modelMapper.map(user, UserLoginServiceModel.class);
    }

    @Override
    public void logout() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }
}
