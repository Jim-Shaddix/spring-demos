package com.example.whoami.dto;

import com.example.whoami.dto.component.AuthDto;
import com.example.whoami.dto.component.RemoteInfoDto;
import com.example.whoami.dto.component.RequestBodyDto;
import com.example.whoami.dto.component.UrlPartsDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class WhoamiDto {

    @JsonProperty("headers")
    private Map<String, String> headers;

    @JsonProperty("url-parts")
    private UrlPartsDto urlParts;

    @JsonProperty("remote-info")
    private RemoteInfoDto remoteInfo;

    @JsonProperty("auth")
    private AuthDto auth;

    @JsonProperty("body")
    private RequestBodyDto body;

    @JsonProperty("hostname")
    private String hostname;

}
