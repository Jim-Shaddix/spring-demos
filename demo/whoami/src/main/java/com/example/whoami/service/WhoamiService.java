package com.example.whoami.service;

import com.example.whoami.config.GeoIpProperties;
import com.example.whoami.config.ParserProperties;
import com.example.whoami.dto.WhoamiDto;
import com.example.whoami.dto.component.RequestBodyDto;
import com.example.whoami.parser.HttpServletRequestParser;
import com.example.whoami.parser.ServerMetadataParser;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    private final ServerMetadataParser serverMetadataParser;
    private final ParserProperties parserProperties;
    private final GeoIpService geoIpService;
    private final GeoIpProperties geoIpProperties;

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
        }

        if (parserProperties.isUrlParts()) {
            whoamiDto.setUrlParts(requestParser.parseRequestUrlParts(request));
        }

        if (parserProperties.isRemoteInfo()) {
            whoamiDto.setRemoteInfo(requestParser.parseRemoteInfo(request));
        }

        if (parserProperties.isAuthInfo()) {
            whoamiDto.setAuth(requestParser.parseAuthInfo(request));
        }

        if (parserProperties.isBody()) {

            RequestBodyDto requestBodyDto = requestParser.parseRequestBody(request);

            if (requestBodyDto.getContent() == null) {
                requestBodyDto.setContent("empty-body");
            }

            whoamiDto.setBody(requestBodyDto);

        }

        if (parserProperties.isHostname()) {
            whoamiDto.setServerMetadataDto(serverMetadataParser.parseServerMetaData());
        }

        if (parserProperties.isGeoIp()) {
            if (geoIpProperties.getApiKey() != null) {
                whoamiDto.setGeolocationDto(geoIpService.getGeoIp(request.getRemoteAddr()));
            } else {
                log.warning("Parser properties are set to parse the geolocation, but NO geolocation API-key could be found. " +
                        "Application is skipping parsing Geo Location data.");
            }
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
                + numberOfRequestsProcessed.incrementAndGet()
                + ". json-response: "
                + whoamiDto
        );
    }

}
