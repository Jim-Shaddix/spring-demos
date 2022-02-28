package com.example.whoami.service;

import com.example.whoami.config.ParserFlags;
import com.example.whoami.parser.HttpServletRequestParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.*;

/**
 * Unit tests for WhoamiService
 */
class WhoamiServiceTest {

    private static HttpServletRequestParser requestParser;
    private static ParserFlags parserFlagsAllTrue;
    private static ParserFlags parserFlagsAllFalse;

    public static int requestCount = 0;

    /**
     * sets up all the mocks for the WhoamiService.
     */
    @BeforeAll
    public static void setup() {

        // setup parserFlagsAllTrue mock
        parserFlagsAllTrue = mock(ParserFlags.class);
        when(parserFlagsAllTrue.isBody()).thenReturn(true);
        when(parserFlagsAllTrue.isUrlParts()).thenReturn(true);
        when(parserFlagsAllTrue.isAuthInfo()).thenReturn(true);
        when(parserFlagsAllTrue.isHeader()).thenReturn(true);
        when(parserFlagsAllTrue.isRemoteInfo()).thenReturn(true);

        // setup parserFlagsAllFalse mock
        parserFlagsAllFalse = mock(ParserFlags.class);
        when(parserFlagsAllFalse.isBody()).thenReturn(false);
        when(parserFlagsAllFalse.isUrlParts()).thenReturn(false);
        when(parserFlagsAllFalse.isAuthInfo()).thenReturn(false);
        when(parserFlagsAllFalse.isHeader()).thenReturn(false);
        when(parserFlagsAllFalse.isRemoteInfo()).thenReturn(false);

        // setup parser mock
        requestParser = mock(HttpServletRequestParser.class);
        when(requestParser.parseRequestHeaders(any())).thenReturn(new HashMap<>());
        when(requestParser.parseAuthInfo(any())).thenReturn(new HashMap<>());
        when(requestParser.parseRemoteInfo(any())).thenReturn(new HashMap<>());
        when(requestParser.parseRequestUrlParts(any())).thenReturn(new HashMap<>());
        when(requestParser.parseRequestBody(any())).thenReturn(Optional.ofNullable(null));
    }

    /**
     * verifies that every parser is run on the http request
     * when all parsing flags are set to true.
     */
    @Test
    void parseRequestMetadataAllTrueFlags() {

        // setup WhoamiService for test
        WhoamiService whoamiService = new WhoamiService(requestParser, parserFlagsAllTrue);

        Map<String, Object> map = whoamiService.parseRequestMetadata(null);
        verify(requestParser).parseRequestHeaders(any());
        verify(requestParser).parseRequestBody(any());
        verify(requestParser).parseRemoteInfo(any());
        verify(requestParser).parseRequestUrlParts(any());
        verify(requestParser).parseAuthInfo(any());
        assertEquals(5, map.size());
    }

    /**
     * verifies that the httprequest is not parsed when all
     * parsing flags are set to false.
     */
    @Test
    void parseRequestMetadataAllFalseFlags() {

        // setup WhoamiService for test
        WhoamiService whoamiService = new WhoamiService(requestParser, parserFlagsAllFalse);

        Map<String, Object> map = whoamiService.parseRequestMetadata(null);
        assertEquals(0, map.size());
    }

    /**
     * verifies the number of requests processed increases when logRequest is called.
     */
    @Test
    void logRequest() {

        // setup WhoamiService for test
        WhoamiService whoamiService = new WhoamiService(requestParser, parserFlagsAllTrue);

        // reset static count in whoami service, so we know its expected result.
        AtomicLong numRequests = (AtomicLong) getField(whoamiService, "numberOfRequestsProcessed");
        numRequests.set(0);

        Map<String, Object> whoamiMap = new HashMap<>();
        whoamiMap.put("first", "1");
        whoamiMap.put("second", "2");
        whoamiMap.put("third", "3");

        whoamiService.logRequest(whoamiMap);

        assertEquals(1, numRequests.get());

    }

}