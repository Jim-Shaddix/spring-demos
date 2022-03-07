package com.example.whoami.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * properties for determining which parser properties are allowed to be executed.
 */
@Getter
@Setter
@ToString
@ConfigurationProperties("whoami.parser.auth")
public class ParserProperties {

    /**
     * Determines if the body of the http request is
     * will be returned ot the user.
     */
    private boolean body = true;

    /**
     * Determines if the headers of the http request
     * will be returned the client.
     */
    private boolean header = true;

    /**
     * Determines if the url parts of the http request
     * will be returned ot the user.
     */
    private boolean urlParts = true;

    /**
     * Determines if the metadata associated with request
     * regarding the clients' origin is sent back to the client.
     */
    private boolean remoteInfo = true;

    /**
     * Determines if the authentication info associated with
     * the client will be sent back to them.
     */
    private boolean authInfo = true;

    /**
     * Determines if the hostname associated with the machine
     * will be sent back to the client. In the event that the
     * whoami application is running in a docker container,
     * then the containerID will be sent back to the user.
     */
    private boolean hostname = true;

}

