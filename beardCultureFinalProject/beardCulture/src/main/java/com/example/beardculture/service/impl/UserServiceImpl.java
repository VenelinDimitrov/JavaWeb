package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.User;
import com.example.beardculture.model.service.UserRegisterServiceModel;
import com.example.beardculture.repository.UserRepository;
import com.example.beardculture.repository.UserRoleRepository;
import com.example.beardculture.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRegisterServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        User user = modelMapper.map(userRegisterServiceModel, User.class);

        if (userRepository.count() == 0){
            Set<Role> roles = userRoleRepository.findAllBy();

            user.setRoles(roles);
        } else {

           // user.setRoles(Set.of(userRoleRepository.findByRoleName(RoleNameEnum.USER).orElse(null)));
        }

        userRepository.save(user);

        return modelMapper.map(user, UserRegisterServiceModel.class);
    }
}
