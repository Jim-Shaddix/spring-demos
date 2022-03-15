package com.example.whoami.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Tests all expected requests for the WhoamiApplication.
 */
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class WhoamiRestControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    private static final URI uri = URI.create("http://localhost");

    /**
     * verifies the whoami requests to the /api path return valid json.
     */
    @Test
    void whoamiApi() throws Exception {
        mockMvc.perform(get("http://localhost/api/v1/whoami?a=b"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.body.content").value("empty-body"))
                .andExpect(jsonPath("$.url-parts.query-string").value("a=b"));
    }

}
