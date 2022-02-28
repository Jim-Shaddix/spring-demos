package com.example.whoami.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class WhoamiRedirectControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void initRediect() throws Exception {
        mockMvc.perform(get("http://localhost/"))
                .andExpect(redirectedUrlPattern("/whoami?whoami-redirect=true"))
                .andExpect(status().is3xxRedirection());
    }

}