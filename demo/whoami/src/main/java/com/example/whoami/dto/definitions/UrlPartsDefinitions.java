package com.example.whoami.dto.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlPartsDefinitions {

    @JsonProperty("request-method-def")
    private final String requestMethod = "HTTP Method used for specifying " +
            "what server side operation should be performed";

    @JsonProperty("request-url-def")
    private final String requestUrl = "The full url used for acquiring this resource.";

    @JsonProperty("scheme-def")
    private final String scheme = "Communication format used for making the request.";

    @JsonProperty("protocol-def")
    private final String protocol = "The Specific protocol that was utilized for making the request.";

    @JsonProperty("server-host-def")
    private final String serverHost = "Server Host location";

    @JsonProperty("server-port-def")
    private final String serverPort = "Server Port that is actively being " +
            "listened on, and that received this request.";

    @JsonProperty("path-def")
    private final String path = "The path that was used to specify the " +
            "resource the client wanted to interact with.";

    @JsonProperty("query-string-def")
    private final String queryString = "List of key value pairs that are used " +
            "as modifiers to the path that was specified.";

}
