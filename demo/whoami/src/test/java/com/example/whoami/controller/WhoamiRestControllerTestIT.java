package com.example.whoami.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(jsonPath("$.body.content").isEmpty())
                .andExpect(jsonPath("$.url-parts.query-string").value("a=b"));
    }

    @Test
    void invalidDataTest() throws Exception {
        MvcResult mvcResult =  mockMvc.perform(get("http://localhost/api/v1/data?unit=4kkkkB"))
                .andReturn();
                //.andExpect(status().is4xxClientError())

        System.out.println("result: " + mvcResult.getResponse().getContentAsString());

    }

    void assertDataSize(String unit, long expectedStringSize) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("http://localhost/api/v1/data?unit=" + unit))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String resultString = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedStringSize, resultString.length());
    }

    @Test
    void validDataKbTest() throws Exception {
        assertDataSize("4kb", 4096);
    }

    @Test
    void validDataBTest() throws Exception {
        assertDataSize("4b", 4);
    }

    @Test
    void validDataMbTest() throws Exception {
        assertDataSize("1mb", 1_048_576);
    }

    @Test
    void validBenchTest() throws Exception {
        mockMvc.perform(get("http://localhost/api/v1/bench"))
                .andExpect(content().string("1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void validHeadersTest() throws Exception {
        mockMvc.perform(get("http://localhost/api/v1/headers"))
                .andExpect(status().is2xxSuccessful());
    }


}
