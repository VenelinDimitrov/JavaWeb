package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Role;
import com.example.beardculture.model.entity.User;
import com.example.beardculture.model.entity.enums.RoleNameEnum;
import com.example.beardculture.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class BeardCultureUserDetailsServiceImplTest {

    private User testUser;
    private Role adminRole, userRole;
    private BeardCultureUserDetailsServiceImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {
        serviceToTest = new BeardCultureUserDetailsServiceImpl(mockUserRepository);
        //Arrange
        adminRole = new Role();
        adminRole.setRole(RoleNameEnum.ADMIN);

        userRole = new Role();
        userRole.setRole(RoleNameEnum.USER);

        testUser = new User();
        testUser.setUsername("pesho");
        testUser.setPassword("1111");
        testUser.setEmail("pesho@abv.bg");
        testUser.setRoles(Set.of(userRole, adminRole));
    }

    @Test
    public void userNotFound(){
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            serviceToTest.loadUserByUsername("invalidUsername");
        });
    }

    @Test
    public void testUserFound(){
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        var actual = serviceToTest.loadUserByUsername(testUser.getUsername());

        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());

        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";

        Assertions.assertEquals(expectedRoles, actualRoles);
    }
}
