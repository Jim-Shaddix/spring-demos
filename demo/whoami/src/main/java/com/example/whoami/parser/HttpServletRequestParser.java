package com.example.whoami.parser;

import com.example.whoami.dto.component.AuthDto;
import com.example.whoami.dto.component.RemoteInfoDto;
import com.example.whoami.dto.component.RequestBodyDto;
import com.example.whoami.dto.component.UrlPartsDto;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    public Map<String, String> parseRequestHeaders(@NonNull HttpServletRequest request) {

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
    public RequestBodyDto parseRequestBody(@NonNull HttpServletRequest request) {

        RequestBodyDto requestBodyDto = new RequestBodyDto();

        try {

            String requestBody;

            // parsing request body from the request
            requestBody = request.getReader()
                    .lines()
                    .collect(Collectors.joining("\n"));

            // assigning the request body to null, if the request content has zero length
            if (requestBody.length() == 0) {
                requestBody = null;
            }

            requestBodyDto.setContent(requestBody);

        } catch (IOException e) {
            throw new RuntimeException("Failed ot parse the request body.", e);
        }

        return requestBodyDto;
    }

    /**
     * Parses out the different components of a URL.
     *
     * @param request the http request received from the user.
     * @return the different ports of the URL broken out
     */
    public UrlPartsDto parseRequestUrlParts(@NonNull HttpServletRequest request) {

        UrlPartsDto dto = new UrlPartsDto();

        dto.setRequestMethod(request.getMethod());
        dto.setRequestUrl(String.valueOf(request.getRequestURL()));
        dto.setScheme(request.getScheme());
        dto.setProtocol(request.getProtocol());
        dto.setServerHost(String.valueOf(request.getServerName()));
        dto.setServerPort(String.valueOf(request.getServerPort()));
        dto.setPath(request.getServletPath());
        dto.setQueryString(request.getQueryString());

        return dto;
    }

    /**
     * parses details regarding the origins of a request from
     * a servlet request.
     *
     * @param request the http request received from the user.
     * @return metadata that describes the users remote information.
     */
    public RemoteInfoDto parseRemoteInfo(@NonNull HttpServletRequest request) {

        RemoteInfoDto dto = new RemoteInfoDto();

        dto.setRequestAddress(request.getRemoteAddr());
        dto.setRequestHost(request.getRemoteHost());
        dto.setRequestPort(String.valueOf(request.getRemotePort()));

        return dto;
    }

    /**
     * gathers authentication information in regard to the users request.
     *
     * @param request the http request received from the user.
     * @return metadata that describes the users authentication information.
     */
    public AuthDto parseAuthInfo(@NonNull HttpServletRequest request) {

        AuthDto dto = new AuthDto();

        dto.setAuthType(request.getAuthType());
        dto.setRemoteUser(request.getRemoteUser());
        dto.setUserPrincipal(String.valueOf(request.getUserPrincipal()));

        return dto;
    }

    /**
     * parses the hostname of the machine running the whoami application.
     * In the event where this application is running in a docker container,
     * then method will return the containerID.
     * The following post gives details on how to acquire a hostname
     * in java: https://stackoverflow.com/questions/7348711/recommended-way-to-get-hostname-in-java
     * @return hostname of the machine running this application.
     */
    public String parseHostName() {

        String hostname;

        // using unix hostname to get result
        String unixHost = System.getenv("HOSTNAME");
        if (unixHost != null) {
            return unixHost;
        }

        // using windows environment variable to get hostname
        String windowsHost = System.getenv("COMPUTERNAME");
        if (windowsHost != null) {
            return windowsHost;
        }

        // using inet to get hostname
        // this is useful for determining the hostname on Windows systems,
        // in the event that the COMPUTERNAME environment variable is not set.
        try {
            String inetHost = InetAddress.getLocalHost().getHostName();
            if (inetHost.length() != 0) {
                return inetHost;
            }
        } catch (UnknownHostException e) {}

        return null;
    }

}
