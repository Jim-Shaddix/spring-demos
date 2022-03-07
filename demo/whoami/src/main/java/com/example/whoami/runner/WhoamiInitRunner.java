package com.example.whoami.runner;

import com.example.whoami.config.ControllerProperties;
import com.example.whoami.config.ParserProperties;
import lombok.AllArgsConstructor;
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

    private static final String PROPERTY_MSG = "Custom Properties --> ";

    final private ParserProperties parserProperties;

    final private ControllerProperties controllerProperties;

    /**
     * wrights log messages describing the properties that were used to run this application.
     */
    @Override
    public void run(String... args) {
        log.info(PROPERTY_MSG + parserProperties.toString());
        log.info(PROPERTY_MSG + controllerProperties.toString());
    }

}
