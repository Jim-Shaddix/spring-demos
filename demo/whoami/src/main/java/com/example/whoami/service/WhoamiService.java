package com.example.whoami.service;

import com.example.whoami.config.ParserProperties;
import com.example.whoami.dto.WhoamiDto;
import com.example.whoami.parser.HttpServletRequestParser;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    private final ParserProperties parserProperties;

    /**
     * Parses metadata content from a http request into the following sections.
     * 1. request headers
     * 2. request body
     * 3. request url components
     * 4. remote info
     * 5. hostname
     *
     * @param request http request received from the WhoamiController
     * @return metadata describing the http request.
     */
    public WhoamiDto parseRequestMetadata(@NonNull HttpServletRequest request) {

        WhoamiDto whoamiDto = new WhoamiDto();

        if (parserProperties.isBody()) {
            whoamiDto.setHeaders(requestParser.parseRequestHeaders(request));
        };

        if(parserProperties.isUrlParts()) {
            whoamiDto.setUrlParts(requestParser.parseRequestUrlParts(request));
        }

        if(parserProperties.isRemoteInfo()) {
            whoamiDto.setRemoteInfo(requestParser.parseRemoteInfo(request));
        }

        if(parserProperties.isAuthInfo()) {
            whoamiDto.setAuth(requestParser.parseAuthInfo(request));
        }

        if(parserProperties.isBody()) {

            Optional<String> requestBody = requestParser.parseRequestBody(request);

            if (requestBody.isPresent()) {
                whoamiDto.setBody(requestBody.get());
            } else {
                whoamiDto.setBody("empty-body");
            }

        }

        if(parserProperties.isHostname()) {
            whoamiDto.setHostname(requestParser.parseHostName());
        }

        return whoamiDto;
    }

    /**
     * logs a message of the number of whoami requests that have been
     * received along with the requests' metadata.
     *
     * @param whoamiDto http request metadata parsed from a HttpRequestServlet.
     */
    public void logRequest(@NonNull WhoamiDto whoamiDto) {
        log.info("Number of \"whoami\" requests processed: "
                + String.valueOf(numberOfRequestsProcessed.incrementAndGet())
                + ". json-response: "
                + whoamiDto.toString()
        );
    }

}
