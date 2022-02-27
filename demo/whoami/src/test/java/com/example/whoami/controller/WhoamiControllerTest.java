package com.example.whoami.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@AutoConfigureMockMvc
@SpringBootTest
class WhoamiControllerTest {

    @Autowired
    MockMvc mockMvc;

    private static final URI uri = URI.create("http://localhost");

    @Test
    void initRediect() throws Exception {
        mockMvc.perform(get("http://localhost/"))
                .andExpect(redirectedUrlPattern("/whoami?whoami-redirect=true"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void whoamiApi() throws Exception {
        mockMvc.perform(get("http://localhost/api/whoami/random-path-element"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }

    @Test
    void whoamiHtml() throws Exception {
        mockMvc.perform(get("http://localhost/whoami/random-path-element"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().contentTypeCompatibleWith("text/html;charset=UTF-8"));
    }

}