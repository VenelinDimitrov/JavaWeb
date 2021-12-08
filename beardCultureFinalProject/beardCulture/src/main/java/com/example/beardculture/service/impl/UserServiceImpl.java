package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Product;
import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.User;
import com.example.beardculture.model.entity.enums.RoleNameEnum;
import com.example.beardculture.model.service.UserDetailsUpdateServiceModel;
import com.example.beardculture.model.service.UserRegisterServiceModel;
import com.example.beardculture.repository.UserRepository;
import com.example.beardculture.repository.UserRoleRepository;
import com.example.beardculture.service.ProductService;
import com.example.beardculture.service.RoleService;
import com.example.beardculture.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    //TODO delete when done testing
    private final ProductService productService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService, ProductService productService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.productService = productService;
    }

    @Override
    public UserRegisterServiceModel registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        User user = modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));

        if (userRepository.count() == 0) {
            Set<Role> roles = userRoleRepository.findAllBy();

            user.setRoles(roles);
        } else {
            user.setRoles(Set.of(roleService.getRoleByRoleName(RoleNameEnum.USER)));
        }
        userRepository.save(user);
        //TODO delete when done testing
        Product product1 = productService.getProductById(1L);
        Product product2 = productService.getProductById(2L);
        Product product3 = productService.getProductById(3L);

        user.getSubscriptionBox().add(product1);
        user.getSubscriptionBox().add(product2);
        user.getSubscriptionBox().add(product3);
        user.getSubscriptionBox().remove(product2);

        userRepository.save(user);

        return modelMapper.map(user, UserRegisterServiceModel.class);
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUserDetails(UserDetailsUpdateServiceModel userDetails) {
        User currentUser = userRepository.findByUsername(userDetails.getUsername()).orElse(null);

        if (currentUser != null) {
            currentUser.setFirstName(userDetails.getFirstName());
            currentUser.setLastName(userDetails.getLastName());
            currentUser.setPhoneNumber(userDetails.getPhoneNumber());
            currentUser.setAddress(userDetails.getAddress());

            userRepository.save(currentUser);
        }
    }

    @Override
    public void removeProductFromBox(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElse(null);

        Set<Product> newProductList = user.getSubscriptionBox().stream().filter(p -> p.getId() != productId).collect(Collectors.toSet());

        user.setSubscriptionBox(newProductList);

        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
