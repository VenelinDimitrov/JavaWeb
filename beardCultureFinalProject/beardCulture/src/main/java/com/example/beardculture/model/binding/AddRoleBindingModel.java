package com.example.beardculture.model.binding;

import com.example.beardculture.model.entity.enums.RoleNameEnum;

public class AddRoleBindingModel {

    private String email;
    private RoleNameEnum role;

    public AddRoleBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public void setRole(RoleNameEnum role) {
        this.role = role;
    }
}
