package com.example.whoami.service;

import com.example.whoami.dto.component.*;
import com.example.whoami.service.ip.IpDescriptionService;
import com.example.whoami.service.util.BasicDtoDescriptionParser;
import com.example.whoami.service.util.HeaderDtoDescriptionParser;
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

    private final BasicDtoDescriptionParser basicDtoDescriptionParser;

    private final HeaderDtoDescriptionParser headerDtoDescriptionParser;

    private final IpDescriptionService ipDescriptionService;

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
                headerDtoDescriptionParser.setHeaderDescription(requestHeaderDto);
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

        RequestBodyDto dto = new RequestBodyDto();

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

            dto.setContent(requestBody);

        } catch (IOException e) {
            throw new RuntimeException("Failed ot parse the request body.", e);
        }

        basicDtoDescriptionParser.setDescription(dto);
        return dto;
    }

    /**
     * Parses out the different components of a URL.
     *
     * @param request the http request received from the user.
     * @return the different ports of the URL broken out
     */
    public UrlPartsDto parseRequestUrlParts(@NonNull HttpServletRequest request) {

        UrlPartsDto dto = new UrlPartsDto();

        dto.setQueryString(request.getQueryString());
        dto.setUrl(String.valueOf(request.getRequestURL()) + "?" + dto.getQueryString());
        dto.setScheme(request.getScheme());
        dto.setProtocol(request.getProtocol());
        dto.setServerHost(String.valueOf(request.getServerName()));
        dto.setHostFormat(ipDescriptionService.parseHostType(request.getServerName()));
        dto.setServerPort(String.valueOf(request.getServerPort()));
        dto.setServerSocket(dto.getServerHost() + ":" + dto.getServerPort());
        dto.setPath(request.getServletPath());

        basicDtoDescriptionParser.setDescription(dto);
        return dto;
    }

    /**
     * Parses metadata regarding the request method specified by the received http request.
     *
     * @param request the http request received from the user.
     * @return metadata that describes the users request method.
     */
    public RequestMethodDto parseRequestMethodDto(@NonNull HttpServletRequest request) {
        RequestMethodDto dto = new RequestMethodDto();
        dto.setRequestMethod(request.getMethod());
        basicDtoDescriptionParser.setDescription(dto);
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
        dto.setRequestHost(ipDescriptionService.parseHostNames(request.getRemoteAddr()));
        dto.setRequestPort(String.valueOf(request.getRemotePort()));
        dto.setRequestSocket(dto.getRequestHost() + ":" + dto.getRequestPort());
        dto.setRequestIpType(ipDescriptionService.parseIpType(request.getRemoteHost()));

        basicDtoDescriptionParser.setDescription(dto);
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

        basicDtoDescriptionParser.setDescription(dto);
        return dto;
    }

    /**
     * gets local information that the server is using to process the request.
     *
     * @param request the http request received from the user.
     * @return metadata describing the local being used to process the request.
     */
    public LocaleDto parseLocale(@NonNull HttpServletRequest request) {

        LocaleDto dto = new LocaleDto();

        Locale local =  request.getLocale();

        dto.setName(local.getDisplayName());
        dto.setCountry(local.getDisplayCountry());
        dto.setLanguage(local.getDisplayLanguage());
        dto.setLanguageTag(local.toLanguageTag());

        //String extensions = local.getExtensionKeys().stream().map(c -> String.valueOf(c)).collect(Collectors.joining(","));
        //dto.setExtensions(extensions);
        //dto.setScript(local.getDisplayScript());
        //dto.setVariant(local.getDisplayVariant());

        basicDtoDescriptionParser.setDescription(dto);

        return dto;
    }

}
