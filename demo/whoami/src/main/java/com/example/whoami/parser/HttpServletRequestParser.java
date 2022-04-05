package com.example.whoami.parser;

import com.example.whoami.dto.HeaderSpec;
import com.example.whoami.dto.component.*;
import com.example.whoami.dto.description.BasicDescriptionDto;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Methods for Parsing metadata from HttpServletRequests.
 */
@Service
@AllArgsConstructor
public class HttpServletRequestParser {

    private BasicDtoDescriptionParser basicDtoDescriptionParser;

    private List<HeaderSpec> headerSpecs;

    private void setDescription(BasicDescriptionDto basicDescriptionDto) {
        Map<String, String> map = basicDtoDescriptionParser
                .parseDtoDescription(basicDescriptionDto.getClass());
        basicDescriptionDto.setDefinition(map);
    }

    private void setHeaderDescription(RequestHeaderDto requestHeaderDto) {

        // find spec for the request dto
        Optional<HeaderSpec> optionalSpec = headerSpecs.stream()
            .filter(spec -> {
                return spec.getName()
                        .equalsIgnoreCase(requestHeaderDto.getName());
            }).findAny();

        // if the spec was found, then set the fields in the DTO.
        if (optionalSpec.isPresent()) {
            HeaderSpec spec = optionalSpec.get();
            requestHeaderDto.setType(String.valueOf(spec.getType()));
            requestHeaderDto.setType(spec.getType());
            requestHeaderDto.setLink(spec.getLink());
            requestHeaderDto.setDescription(spec.getDescription());
        }

    }

    /**
     * parses the http headers from the HttpServletRequest and
     * returns them as a map of header-name -> header-field.
     *
     * @param request the http request received.
     * @return the http headers that were present in the request received.
     */
    public List<RequestHeaderDto> parseRequestHeaders(@NonNull HttpServletRequest request) {

        List<RequestHeaderDto> requestHeaderDtos = new ArrayList<>();

        Enumeration<String> headerEnum = request.getHeaderNames();

        // set the request header DTOs
        {
            String currHeaderName;
            String currHeaderValue;
            while (headerEnum.hasMoreElements()) {

                // parse header information
                currHeaderName = headerEnum.nextElement();
                currHeaderValue = request.getHeader(currHeaderName);

                // set header information in a dto
                RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
                requestHeaderDto.setName(currHeaderName);
                requestHeaderDto.setValue(currHeaderValue);
                setHeaderDescription(requestHeaderDto);
                requestHeaderDtos.add(requestHeaderDto);
            }
        }

        return requestHeaderDtos;
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

        setDescription(requestBodyDto);
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
        dto.setServerSocket(dto.getServerHost() + ":" + dto.getServerPort());
        dto.setPath(request.getServletPath());
        dto.setQueryString(request.getQueryString());

        setDescription(dto);
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
        dto.setRequestSocket(dto.getRequestHost() + ":" + dto.getRequestPort());

        setDescription(dto);
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

        setDescription(dto);
        return dto;
    }

}
