package com.example.whoami.webparser.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties that govern the execution of the web-parser.
 */
@Setter
@Getter
@ConfigurationProperties("whoami.webparser")
public class WebParserConfig {

    /**
     * Forces the webparser to be executed again. By default, the
     * parser will not be executed if the location specified by
     * httpHeaderSpecLocation already has a json file.
     */
    private boolean forceParserExecution = false;

    /**
     * The location used for reading in the httpHeaderSpec.
     */
    private String httpHeaderSpecLocation;

}
