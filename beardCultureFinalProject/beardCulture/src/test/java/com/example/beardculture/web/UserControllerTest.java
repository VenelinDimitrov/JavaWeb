package com.example.beardculture.web;

import com.example.beardculture.model.entity.User;
import com.example.beardculture.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String TEST_USER_EMAIL = "pesho@abv.bg";
    private static final String TEST_USER_FIRST_NAME = "Pesho";
    private static final String TEST_USER_LAST_NAME = "Petrov";
    private static final String TEST_USER_USERNAME = "pesho";
    private static final String TEST_USER_PASSWORD = "1111";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void testOpenLogin() throws Exception {
        mockMvc.perform(get("/users/login")).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.perform(get("/users/register")).andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testUnsuccessfulRegisterUser() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("firstName", TEST_USER_FIRST_NAME)
                        .param("lastName", TEST_USER_LAST_NAME)
                        .param("username", TEST_USER_USERNAME)
                        .param("email", TEST_USER_EMAIL)
                        .param("password", "111")
                        .param("repeatPassword", "111")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is4xxClientError());

        Assertions.assertEquals(0, userRepository.count());
    }

    @Test
    void testRegisterUser() throws Exception {

        mockMvc.perform(post("/users/register")
                .param("firstName", TEST_USER_FIRST_NAME)
                .param("lastName", TEST_USER_LAST_NAME)
                .param("username", TEST_USER_USERNAME)
                .param("email", TEST_USER_EMAIL)
                .param("password", TEST_USER_PASSWORD)
                .param("repeatPassword", TEST_USER_PASSWORD)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<User> newlyCreatedUserOpt = userRepository.findByEmail(TEST_USER_EMAIL);

        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());

        User newlyCreatedUser = newlyCreatedUserOpt.get();

        Assertions.assertEquals(newlyCreatedUser.getUsername(), TEST_USER_USERNAME);
        Assertions.assertEquals(newlyCreatedUser.getFirstName(), TEST_USER_FIRST_NAME);
        Assertions.assertEquals(newlyCreatedUser.getLastName(), TEST_USER_LAST_NAME);
        Assertions.assertEquals(newlyCreatedUser.getEmail(), TEST_USER_EMAIL);
    }
}
