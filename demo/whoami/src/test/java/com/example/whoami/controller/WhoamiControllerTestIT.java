package com.example.whoami.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


/**
 * Tests all expected requests for the WhoamiApplication.
 */
@AutoConfigureMockMvc
@SpringBootTest
class WhoamiControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    private static final URI uri = URI.create("http://localhost");

    /**
     * verifies the whoami requests to the /api path return valid json.
     */
    @Test
    void whoamiApi() throws Exception {
        mockMvc.perform(get("http://localhost/api/whoami/random-path-element"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().contentTypeCompatibleWith("application/json"));
                //.andExpect(jsonPath("random-path-element", "$.url-parts.path"))
    }

    /**
     * verifies the whoami requests to the /api path return valid json.
     */
    @Test
    void whoamiHtml() throws Exception {
        mockMvc.perform(get("http://localhost/whoami/random-path-element"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().contentTypeCompatibleWith("text/html;charset=UTF-8"));
    }

}