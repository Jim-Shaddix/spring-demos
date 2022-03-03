package com.example.whoami;

import com.example.whoami.property.ParserProperties;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.env.Environment;

@Log
@SpringBootApplication
@ConfigurationPropertiesScan("com.example.whoami.property")
public class WhoamiApplication implements CommandLineRunner {

    @Autowired
    Environment environment;

    @Autowired
    ParserProperties parserProperties;

    public static void main(String[] args) {
        SpringApplication.run(WhoamiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

       log.info("Running WhoamiApp with ParserProperties: " + parserProperties.toString());

       if (environment.getActiveProfiles().length == 0) {
           throw new RuntimeException("Failed to find an active-profile. " +
                   "You will need to set the active profile to either \"dev\", \"prod\", or \"test\".");
       }

    }
}
