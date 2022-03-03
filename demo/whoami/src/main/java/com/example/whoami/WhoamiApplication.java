package com.example.whoami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;

import javax.websocket.RemoteEndpoint;

@SpringBootApplication
public class WhoamiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhoamiApplication.class, args);
    }

}
