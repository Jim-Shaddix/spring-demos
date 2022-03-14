package com.example.whoami.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UrlPartsDto {

    @JsonProperty("request-method")
    private String requestMethod;

    @JsonProperty("request-url")
    private String requestUrl;

    @JsonProperty("scheme")
    private String scheme;

    @JsonProperty("protocol")
    private String protocol;

    @JsonProperty("server-host")
    private String serverHost;

    @JsonProperty("server-port")
    private String serverPort;

    @JsonProperty("path")
    private String path;

    @JsonProperty("query-string")
    private String queryString;

}
