package com.example.whoami.dto;

import com.example.whoami.dto.component.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "contains all of the metadata associated with the client initial request.")
public class WhoamiDto {

    @JsonProperty("headers")
    private List<RequestHeaderDto> headers;

    @JsonProperty("url-parts")
    private UrlPartsDto urlParts;

    @JsonProperty("remote-info")
    private RemoteInfoDto remoteInfo;

    @JsonProperty("auth")
    private AuthDto auth;

    @JsonProperty("body")
    private RequestBodyDto body;

    @JsonProperty("server-info")
    private ServerMetadataDto serverMetadataDto;

    @JsonProperty("geolocation")
    private GeolocationDto geolocationDto;

    @JsonProperty("request-method")
    private RequestMethodDto requestMethod;
}
