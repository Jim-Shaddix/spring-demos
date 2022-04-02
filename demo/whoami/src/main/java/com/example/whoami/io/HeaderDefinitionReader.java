package com.example.whoami.io;

import com.example.whoami.dto.HeaderSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;
import java.util.List;

@Configuration
public class HeaderDefinitionReader {

    @Value("${whoami.io.http-header-spec-location}")
    private String headerSpecLocation;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Reads the JSON file that was previously serialized
     */
    public List<HeaderSpec> readHeaderSpecFile() {

        List<HeaderSpec> specs;
        try {
            specs = objectMapper.readerForListOf(HeaderSpec.class)
                    .readValue(Paths.get(headerSpecLocation).toFile());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read json spec file", e);
        }

        return specs;
    }

    @Bean
    public List<HeaderSpec> getHeaderSpecs() {
        return readHeaderSpecFile();
    }
}
