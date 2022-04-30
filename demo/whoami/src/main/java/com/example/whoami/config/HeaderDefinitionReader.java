package com.example.whoami.config;

import com.example.whoami.dto.HeaderSpec;
import com.example.whoami.dto.RequestMethodSpec;
import com.example.whoami.dto.ResponseCodeSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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

    @Value("${whoami.io.http-response-code-spec-location}")
    private String responseCodeSpecLocation;

    @Value("${whoami.io.http-request-method-spec-location}")
    private String requestMethodSpecLocation;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Reads the JSON file that was previously serialized
     */
    private <R> List<R> readSpecFile(Class<R> spec, String specLocation) {

        List<R> specs;
        try {
            specs = objectMapper.readerForListOf(spec)
                    .readValue(Paths.get(specLocation).toFile());
        } catch (Exception e) {
            String currentDirectory = System.getProperty("user.dir");
            String errMsg = String.format("Failed to read json spec file. [Current Directory: %s] [File Not Found: %s]",
                    currentDirectory, specLocation);
            throw new RuntimeException(errMsg, e);
        }

        return specs;
    }

    @Bean
    public List<HeaderSpec> getHeaderSpecs() {
        return readSpecFile(HeaderSpec.class, headerSpecLocation);
    }

    @Bean
    public List<RequestMethodSpec> getRequestMethodSpecs() {
        return readSpecFile(RequestMethodSpec.class, requestMethodSpecLocation);
    }

    @Bean
    public List<ResponseCodeSpec> getResponseCodeSpecs() {
        return readSpecFile(ResponseCodeSpec.class, responseCodeSpecLocation);
    }

}
