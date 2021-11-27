package com.example.beardculture.model.entity;

import com.example.beardculture.model.entity.enums.RoleNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    private RoleNameEnum role;

    public Role() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public RoleNameEnum getRole() {
        return role;
    }

    public void setRole(RoleNameEnum role) {
        this.role = role;
    }
}
