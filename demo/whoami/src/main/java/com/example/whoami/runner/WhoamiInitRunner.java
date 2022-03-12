package com.example.whoami.runner;

import com.example.whoami.config.ControllerProperties;
import com.example.whoami.config.ParserProperties;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * runs whoami startup procedures
 */
@Log
@Service
@AllArgsConstructor
public class WhoamiInitRunner implements CommandLineRunner {

    final private ControllerProperties controllerProperties;
    final private ParserProperties parserProperties;

    /**
     * wrights log messages describing the properties that were used to run this application.
     * https://reflectoring.io/spring-webclient/
     */
    @Override
    public void run(String... args) {
        String PROPERTY_MSG = "Custom Properties --> ";
        log.info(PROPERTY_MSG + parserProperties.toString());
        log.info(PROPERTY_MSG + controllerProperties.toString());
    }

}
