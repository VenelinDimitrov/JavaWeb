package com.example.musicdb.service.impl;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.entity.User;
import com.example.musicdb.model.service.UserServiceModel;
import com.example.musicdb.repository.UserRepository;
import com.example.musicdb.sec.CurrentUser;
import com.example.musicdb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);

        userRepository.save(user);

        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).orElse(null);

        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public void logoutUser() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }
}
