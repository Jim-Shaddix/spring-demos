package com.example.whoami.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for determining which controllers to load.
 */
@Getter
@Setter
@ToString
@ConfigurationProperties("whoami.controller")
public class ControllerProperties {

    /**
     * Determines if the WhoamiErrorController bean will be loaded into the context.
     * If the whoami project is being used as a library for another project, it may
     * be useful to disable this controller to prevent unexpected behavior.
     */
    boolean enableErrorController;

    /**
     * Determines if the WhoamiRedirectController bean will be loaded into the context.
     * If the whoami project is being used as a library for another project, it may
     * be useful to disable this controller to prevent unexpected behavior.
     */
    boolean enableRedirectController;

}