package com.example.whoami.service;

import com.example.whoami.property.ParserProperties;
import com.example.whoami.parser.HttpServletRequestParser;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * contains methods for processing requests received
 * from the WhoamiController.java.
 */
@AllArgsConstructor
@Service
@Log
public class WhoamiService {

    private static final AtomicLong numberOfRequestsProcessed = new AtomicLong(0);

    private final HttpServletRequestParser requestParser;
    private final ParserProperties parserFlags;

    /**
     * Parses metadata content from a http request into the following sections.
     * 1. request headers
     * 2. request body
     * 3. request url components
     * 4. remote info
     *
     * @param request http request recieved from the WhoamiController
     * @return metadata describing the http request.
     */
    public Map<String, Object> parseRequestMetadata(@NonNull HttpServletRequest request) {

        Map<String, Object> whoamiMap = new LinkedHashMap<>();

        if (parserFlags.isBody()) {
            whoamiMap.put("headers", requestParser.parseRequestHeaders(request));
        };

        if(parserFlags.isUrlParts()) {
            whoamiMap.put("url-parts", requestParser.parseRequestUrlParts(request));
        }

        if(parserFlags.isRemoteInfo()) {
            whoamiMap.put("remote-info", requestParser.parseRemoteInfo(request));
        }

        if(parserFlags.isAuthInfo()) {
            whoamiMap.put("auth", requestParser.parseAuthInfo(request));
        }

        if(parserFlags.isBody()) {

            Optional<String> requestBody = requestParser.parseRequestBody(request);

            if (requestBody.isPresent()) {
                whoamiMap.put("body", requestBody.get());
            } else {
                whoamiMap.put("body", "empty-body");
            }

        }

        return whoamiMap;
    }

    /**
     * logs a message of the number of whoami requests that have been
     * received along with the requests' metadata.
     *
     * @param whoamiMap http request metadata parsed from a HttpRequestServlet.
     */
    public void logRequest(@NonNull Map<String, Object> whoamiMap) {
        log.info("Number of \"whoami\" requests processed: "
                + String.valueOf(numberOfRequestsProcessed.incrementAndGet())
                + ". json-response: "
                + whoamiMap.toString()
        );
    }

}
