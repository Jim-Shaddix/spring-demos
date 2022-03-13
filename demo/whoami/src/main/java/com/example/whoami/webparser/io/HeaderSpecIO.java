package com.example.whoami.webparser.io;

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
     * @throws IOException
     */
    public void wrightHeaderSpecFile(List<HeaderSpec> headerSpecs) throws IOException {
        try {
            objectMapper.writeValue(Paths.get(headerSpecLocation).toFile(), headerSpecs);
        } catch (IOException e) {
            e.printStackTrace();
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
            throw new RuntimeException("Failed to read json spec file", e);
        }

        return specs;
    }

}
