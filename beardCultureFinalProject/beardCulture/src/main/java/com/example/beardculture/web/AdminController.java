package com.example.beardculture.web;

import com.example.beardculture.model.binding.AddRoleBindingModel;
import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.User;
import com.example.beardculture.service.RoleService;
import com.example.beardculture.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;

        this.roleService = roleService;
    }


    @GetMapping("/panel")
    public String adminPanel() {
        return "admin-panel";
    }

    @GetMapping("/addRights")
    public String addRights(AddRoleBindingModel addRoleBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        User user = userService.getUserByEmail(addRoleBindingModel.getEmail());

        if (user != null){

            if (addRoleBindingModel.getAction().equals("Add Role")) {
                Role role = roleService.getRoleByRoleName(addRoleBindingModel.getRole());

                user.getRoles().add(role);
            } else {
                Set<Role> newRoles = user.getRoles().stream().filter(r -> r.getRole() != addRoleBindingModel.getRole())
                        .collect(Collectors.toSet());

                user.setRoles(newRoles);
            }

            userService.saveUser(user);
        }

        return "redirect:/admin/panel";
    }

    @ModelAttribute
    public AddRoleBindingModel addRoleBindingModel(){
        return new AddRoleBindingModel();
    }
}
