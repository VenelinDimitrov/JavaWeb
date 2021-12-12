package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.enums.RoleNameEnum;
import com.example.beardculture.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    private Role testRole;
    private RoleServiceImpl serviceToTest;

    @Mock
    private RoleRepository mockRoleRepository;

    @BeforeEach
    public void init(){
        serviceToTest = new RoleServiceImpl(mockRoleRepository);

        testRole = new Role();
        testRole.setRole(RoleNameEnum.ADMIN);
    }

    @Test
    public void testRoleFoundByName(){
        Mockito.when(mockRoleRepository.findByRole(testRole.getRole()))
                .thenReturn(Optional.of(testRole));

        var actual = serviceToTest.getRoleByRoleName(testRole.getRole());

        Assertions.assertEquals(actual.getRole(), testRole.getRole());
    }
}
