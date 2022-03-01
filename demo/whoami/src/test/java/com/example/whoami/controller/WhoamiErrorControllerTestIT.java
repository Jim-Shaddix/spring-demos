package com.example.whoami.controller;

import com.example.whoami.service.WhoamiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Tests all Errors for the WhoamiApplication.
 */
@AutoConfigureMockMvc
@SpringBootTest
class WhoamiErrorControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void handleError404() throws Exception {
        mockMvc.perform(get("http://localhost/random-path"))
                .andExpect(status().is(404))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void handleError500() throws Exception {}

}