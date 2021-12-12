package com.example.beardculture.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testOpenHomePage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testOpenContactPage() throws Exception {
        mockMvc.perform(get("/contacts")).andExpect(status().isOk())
                .andExpect(view().name("contact-us"));
    }

    @Test
    void testOpenPrivacyPage() throws Exception {
        mockMvc.perform(get("/privacy")).andExpect(status().isOk())
                .andExpect(view().name("privacy-policy"));
    }

    @Test
    void testOpenAboutPage() throws Exception {
        mockMvc.perform(get("/about")).andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

    @Test
    void testOpenTermsPage() throws Exception {
        mockMvc.perform(get("/terms")).andExpect(status().isOk())
                .andExpect(view().name("terms"));
    }
}
