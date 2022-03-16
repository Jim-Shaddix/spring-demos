package com.example.whoami.webparser.config;

import com.example.whoami.webparser.service.WebParserService;
import com.example.whoami.webparser.spec.HeaderSpec;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class WebParserConfig {

    private final WebParserService webParserService;

    @Bean
    public List<HeaderSpec> getHeaderSpecs() throws Exception {
       return webParserService.fetchHeaderSpecs();
    }

}
