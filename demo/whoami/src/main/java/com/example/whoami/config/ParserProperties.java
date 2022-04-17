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
     * Determines if the request method will be parsed
     * from the http request.
     */
    private boolean method = true;

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

    /**
     * Determines if the locale that the server uses to process a request
     * should have its metadata sent back to the user.
     */
    private boolean locale = true;

    /**
     * Determines if the GeoIP services will be utilized for sending location data
     * back to the user.
     */
    private boolean geoIp = false;

}

