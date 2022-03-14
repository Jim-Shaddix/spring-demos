package com.example.whoami.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


/**
 * Tests all expected requests for the WhoamiApplication.
 */
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class WhoamiWebControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    private static final URI uri = URI.create("http://localhost");

    /**
     * verifies requests to the whoami html page are completed with a valid response.
     */
    @Test
    void whoamiHtml() throws Exception {
        mockMvc.perform(get("http://localhost/whoami?a=b"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().contentTypeCompatibleWith("text/html;charset=UTF-8"));
    }

    @Test
    void headerViewTest() throws Exception {
        mockMvc.perform(get("http://localhost/headers"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("headers"));
    }
}