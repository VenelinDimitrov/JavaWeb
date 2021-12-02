package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.User;
import com.example.beardculture.model.entity.enums.RoleNameEnum;
import com.example.beardculture.model.service.UserRegisterServiceModel;
import com.example.beardculture.repository.UserRepository;
import com.example.beardculture.repository.UserRoleRepository;
import com.example.beardculture.service.RoleService;
import com.example.beardculture.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserRegisterServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        User user = modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));

        if (userRepository.count() == 0){
            Set<Role> roles = userRoleRepository.findAllBy();

            user.setRoles(roles);
        } else {
           user.setRoles(Set.of(roleService.getRoleByRoleName(RoleNameEnum.USER)));
        }

        userRepository.save(user);

        return modelMapper.map(user, UserRegisterServiceModel.class);
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }
}
