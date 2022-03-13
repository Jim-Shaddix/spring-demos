package com.example.whoami.dto;

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
    private Map<String, String> urlParts;

    @JsonProperty("remote-info")
    private Map<String, String> remoteInfo;

    @JsonProperty("auth")
    private Map<String, String> auth;

    @JsonProperty("body")
    private String body;

    @JsonProperty("hostname")
    private String hostname;

}
