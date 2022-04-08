package com.example.whoami.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Whoami API Definition",
                description = "Provides the API definitions for the whoami application",
                contact = @Contact(
                        name = "James Shaddix",
                        url = "https://jshaddix.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/Jim-Shaddix/spring-demos/tree/main/demo/whoami")
        ),
        servers = {
                @Server(url = "http://whoamiee:443", description = "Production - HTTPS"),
                @Server(url = "http://whoamiee:80", description = "Production - HTTP"),
                @Server(url = "http://localhost:80", description = "Local - Docker Image"),
                @Server(url = "http://127.0.0.1:80", description = "Local - Docker Image"),
                @Server(url = "http://localhost:8080", description = "Local - Jar"),
                @Server(url = "http://127.0.0.1:8080", description = "Local - Jar")
        }
)
public class OpenApiConfiguration {
}
