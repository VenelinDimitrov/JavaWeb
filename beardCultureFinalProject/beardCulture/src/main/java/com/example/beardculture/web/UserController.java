package com.example.beardculture.web;

import com.example.beardculture.model.binding.UpdateUserDetailsBindingModel;
import com.example.beardculture.model.binding.UserRegisterBindingModel;
import com.example.beardculture.model.entity.User;
import com.example.beardculture.model.service.UserDetailsUpdateServiceModel;
import com.example.beardculture.model.service.UserRegisterServiceModel;
import com.example.beardculture.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/account")
    public String myAccount(Principal principal, Model model) {
        User user = userService.getUserByUsername(principal.getName());

        model.addAttribute("currentUser", user);

        return "my-account";
    }

    @PatchMapping("/update")
    public String updateDetails (@Valid UpdateUserDetailsBindingModel updateUserDetailsBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("offerModel", updateUserDetailsBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateUserDetailsBindingModel", bindingResult);

            return "redirect:my-account";
        }

        UserDetailsUpdateServiceModel userDetailsUpdateServiceModel = modelMapper.map(updateUserDetailsBindingModel,
                UserDetailsUpdateServiceModel.class);
        userDetailsUpdateServiceModel.setUsername(principal.getName());

        userService.updateUserDetails(userDetailsUpdateServiceModel);

        return "redirect:account";
    }

    @GetMapping("/dataForAdmin")
    public ResponseEntity<Map<String,Integer>> getAdminData (){
        Map<String, Integer> adminData = new HashMap<>();

        List<User> allUsers = userService.getAllUsers();
        adminData.put("usersCount", allUsers.size());

        int allItemsInSubscriptions = allUsers.stream().mapToInt(user -> user.getSubscriptionBox().size()).sum();

        adminData.put("itemsInSubscriptions", allItemsInSubscriptions);
        adminData.put("itemsPerUser", allItemsInSubscriptions / allUsers.size());

        return ResponseEntity.ok(adminData);
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }
}
