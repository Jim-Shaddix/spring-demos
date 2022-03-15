package com.example.whoami.dto.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Schema(description = "Information that describes the different components of a url!")
public class UrlPartsDto {

    @Schema(description = "HTTP Method used for specifying " +
            "what server side operation should be performed")
    @JsonProperty("request-method")
    private String requestMethod;

    @Schema(description = "The full url used for acquiring this resource.")
    @JsonProperty("request-url")
    private String requestUrl;

    @Schema(description = "Communication format used for making the request.")
    @JsonProperty("scheme")
    private String scheme;

    @Schema(description = "The Specific protocol that was utilized for making the request.")
    @JsonProperty("protocol")
    private String protocol;

    @Schema(description = "Server Host location")
    @JsonProperty("server-host")
    private String serverHost;

    @Schema(description = "Server Port that is actively being " +
            "listened on, and that received this request.")
    @JsonProperty("server-port")
    private String serverPort;

    @Schema(description = "The path that was used to specify the " +
            "resource the client wanted to interact with.")
    @JsonProperty("path")
    private String path;

    @Schema(description = "List of key value pairs that are used " +
            "as modifiers to the path that was specified.")
    @JsonProperty("query-string")
    private String queryString;

}
