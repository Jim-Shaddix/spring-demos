package com.example.whoami.webparser.io;

import com.example.whoami.webparser.exception.WebParserException;
import com.example.whoami.webparser.spec.HeaderSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HeaderSpecIO {

    @Value("${whoami.webparser.http-header-spec-location}")
    private String headerSpecLocation;

    private final ObjectMapper objectMapper;

    /**
     * Serialize all the header definitions to a JSON file.
     */
    public void wrightHeaderSpecFile(List<HeaderSpec> headerSpecs) {
        try {
            objectMapper.writeValue(Paths.get(headerSpecLocation).toFile(), headerSpecs);
        } catch (IOException e) {
            throw new WebParserException("Failed while trying to wright the " +
                    "Header Specifications to a JSON file!", e);
        }
    }

    /**
     * Reads the JSON file that was previously serialized
     */
    public List<HeaderSpec> readHeaderSpecFile() {

        List<HeaderSpec> specs;
        try {
            specs = objectMapper.readerForListOf(HeaderSpec.class)
                    .readValue(Paths.get(headerSpecLocation).toFile());
        } catch (Exception e) {
            throw new WebParserException("Failed to read json spec file", e);
        }

        return specs;
    }

}
