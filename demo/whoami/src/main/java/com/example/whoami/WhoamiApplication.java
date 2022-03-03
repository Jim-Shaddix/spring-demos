package com.example.whoami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.whoami.config")
public class WhoamiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhoamiApplication.class, args);
    }

}
