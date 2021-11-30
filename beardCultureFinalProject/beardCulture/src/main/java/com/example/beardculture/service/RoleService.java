package com.example.beardculture.service;

import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.enums.RoleNameEnum;

public interface RoleService {
    Role getRoleByRoleName(RoleNameEnum roleName);
}
