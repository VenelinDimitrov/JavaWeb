package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.enums.RoleNameEnum;
import com.example.beardculture.repository.RoleRepository;
import com.example.beardculture.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByRoleName(RoleNameEnum roleName) {
        return roleRepository.findByRole(roleName).orElse(null);
    }
}
