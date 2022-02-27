package com.example.whoami.parser;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Methods for Parsing metadata from HttpServletRequests.
 */
@Service
public class HttpServletRequestParser {

    /**
     * parses the http headers from the HttpServletRequest and
     * returns them as a map of header-name -> header-field.
     *
     * @param request the http request received.
     * @return the http headers that were present in the request received.
     */
    public Map<String, String> parseRequestHeaders(HttpServletRequest request) {

        Map<String, String> headerMap = new LinkedHashMap<>();

        Enumeration<String> headerEnum = request.getHeaderNames();

        String currHeader;
        while(headerEnum.hasMoreElements()) {
            currHeader = headerEnum.nextElement();
            headerMap.put(currHeader, request.getHeader(currHeader));
        }

        return headerMap;
    }

    /**
     * parses out the body of the http request received from the servlet request passed in.
     *
     * @param request the http servlet request being processed.
     * @return the body of the http request that was received.
     */
    public Optional<String> parseRequestBody(HttpServletRequest request) {

        String requestBody;

        try {

            // parsing request body from the request
            requestBody = request.getReader()
                    .lines()
                    .collect(Collectors.joining("\n"));

            // assigning the request body to null, if the request content has zero length
            if (requestBody.length() == 0) {
                requestBody = null;
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed ot parse the request body.", e);
        }

        return Optional.ofNullable(requestBody);
    }

    /**
     * Parses out the different components of a URL.
     *
     * @param request the http request received from the user.
     * @return the different ports of the URL broken out
     */
    public Map<String, String> parseRequestUrlParts(HttpServletRequest request) {

        Map<String, String> urlParts = new LinkedHashMap<>();
        urlParts.put("request-method", request.getMethod());
        urlParts.put("request-url", String.valueOf(request.getRequestURL()));
        urlParts.put("scheme", request.getScheme());
        urlParts.put("protocol", request.getProtocol());
        urlParts.put("server-host", String.valueOf(request.getServerName()));
        urlParts.put("server-port", String.valueOf(request.getServerPort()));
        urlParts.put("path", request.getServletPath());
        urlParts.put("query-string", request.getQueryString());

        return urlParts;
    }

    /**
     * parses details regarding the origins of a request from
     * a servlet request.
     *
     * @param request the http request received from the user.
     * @return metadata that describes the users remote information.
     */
    public Map<String, String> parseRemoteInfo(HttpServletRequest request) {

        Map<String, String> remoteInfo = new LinkedHashMap<>();

        remoteInfo.put("request-address", request.getRemoteAddr());
        remoteInfo.put("request-host", request.getRemoteHost());
        remoteInfo.put("request-port", String.valueOf(request.getRemotePort()));

        return remoteInfo;
    }

    /**
     * gathers authentication information in regard to the users request.
     *
     * @param request the http request received from the user.
     * @return metadata that describes the users authentication information.
     */
    public Map<String, String> parseAuthInfo(HttpServletRequest request) {

        Map<String, String> parsedAuthInfo = new LinkedHashMap<>();

        parsedAuthInfo.put("auth-type", request.getAuthType());
        parsedAuthInfo.put("remote-user", request.getRemoteUser());
        parsedAuthInfo.put("user-principal", String.valueOf(request.getUserPrincipal()));

        return parsedAuthInfo;
    }

}
