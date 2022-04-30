package com.example.whoami.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ToString
@ConfigurationProperties("whoami.io")
public class IoProperties {

    /**
     * Location of the header specifications.
     */
    private String httpHeaderSpecLocation;

    /**
     * Location of the response code specifications.
     */
    private String httpResponseCodeSpecLocation;

    /**
     * Location of the request method specifications
     */
    private String httpRequestMethodSpecLocation;
}
