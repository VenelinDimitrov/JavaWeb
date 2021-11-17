package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.Service.UserLoginServiceModel;
import com.example.coffeeshop.model.Service.UserRegisterServiceModel;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.view.UserViewModel;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.sec.CurrentUser;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public UserRegisterServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        User user = modelMapper.map(userRegisterServiceModel, User.class);

        userRepository.save(user);

        return modelMapper.map(user, UserRegisterServiceModel.class);
    }

    @Override
    public UserLoginServiceModel findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).orElse(null);

        UserLoginServiceModel userLoginServiceModel = modelMapper.map(user, UserLoginServiceModel.class);

        return userLoginServiceModel;
    }

    @Override
    public void loginUser(UserLoginServiceModel potentialUser) {
        this.currentUser.setUsername(potentialUser.getUsername());
        this.currentUser.setId(potentialUser.getId());
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserViewModel> getAllUsersByOrderCountDesc() {
        return userRepository.findAllUsers().stream().map(user -> {
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setUsername(user.getUsername());
            userViewModel.setNumberOfOrders(user.getOrders().size());

            return userViewModel;
        }).collect(Collectors.toList());
    }
}
