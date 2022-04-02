package com.example.whoami.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests all Errors for the WhoamiApplication.
 */
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
class WhoamiErrorControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ResourceLoader resourceLoader;

    private byte[] readHtmlAsBytes() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/error-404.html");
        InputStream stream404 = resource.getInputStream();
        InputStreamReader streamReader404 = new InputStreamReader(stream404, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader404);
        String text404 = bufferedReader.lines().collect(Collectors.joining("\n"));
        return text404.getBytes(StandardCharsets.UTF_8);
    }

    @Test
    void handleError404() throws Exception {

        MvcResult result = mockMvc.perform(get("http://localhost:8080/random-path").contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(404))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        System.out.println("content: " + content);

    }

    @Test
    void handleError500() throws Exception {}

}