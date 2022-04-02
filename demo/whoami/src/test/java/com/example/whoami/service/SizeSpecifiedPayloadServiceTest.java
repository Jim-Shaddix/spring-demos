package com.example.whoami.service;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SizeSpecifiedPayloadServiceTest {

    private static final boolean PRINT_RESULTS = false;

    private static final SizeSpecifiedPayloadService sizeSpecifiedPayloadService = new SizeSpecifiedPayloadService();

    public void testPayload(String requestSize, long expectedSize) {

        String result = sizeSpecifiedPayloadService.generatePayloadFromUnitSpec(requestSize);

        if (PRINT_RESULTS) {
            System.out.println("Request Size: " + requestSize);
            System.out.println("Actual Size: " + result.getBytes(StandardCharsets.UTF_8).length);
            System.out.println("result string: " + result);
        }

        assertEquals(expectedSize, result.getBytes(StandardCharsets.UTF_8).length);
    }

    @Test
    public void generatePayload0b() {
        testPayload("0b", 0);
    }

    @Test
    public void generatePayload1b() {
        testPayload("1b", 1);
    }

    @Test
    public void generatePayload100b() {
        testPayload("100b", 100);
    }

    @Test
    public void generatePayload1kb() {
        testPayload("1kb", 1024);
    }

    @Test
    public void generatePayload2kb() {
        testPayload("2kb", 2048);
    }

    @Test
    public void generatePayload1mb() {
        testPayload("1mb", 1048576);
    }

}