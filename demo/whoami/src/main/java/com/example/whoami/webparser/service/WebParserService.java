package com.example.whoami.webparser.service;

import com.example.whoami.webparser.generalparser.GeneralParser;
import com.example.whoami.webparser.io.HeaderSpecIO;
import com.example.whoami.webparser.spec.HeaderSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Log
@Service
@RequiredArgsConstructor
public class WebParserService {

    private final HeaderSpecIO headerSpecIO;
    private final GeneralParser generalParser;

    @Value("${whoami.webparser.force-parser-execution}")
    private boolean forceExecution;

    @Value("${whoami.webparser.http-header-spec-location}")
    private String headerSpecLocation;

    private boolean jsonHeaderSpecFileExists() {
        return Paths.get(headerSpecLocation).toFile().exists();
    }

    private List<HeaderSpec> executeWebParser() throws IOException {
        List<HeaderSpec> headerSpecs = generalParser.createAllHeaderSpecs();
        log.info("Fetching definitions of http headers ...");
        headerSpecIO.wrightHeaderSpecFile(headerSpecs);
        log.info("Finished fetching, parsing, " +
                "and serializing all of the http header information.");
        return headerSpecs;
    }

    public List<HeaderSpec> fetchHeaderSpecs() throws Exception {

        if (!jsonHeaderSpecFileExists()) {

            log.warning("The Header-Spec-File does not exist! " +
                    "Could not find the following file: " + headerSpecLocation);
            log.warning("Because the spec file could not be found, the webparser is now executing.");
            return executeWebParser();

        } else if (forceExecution) {

            log.warning("The WebParser Force Execution Setting is set to TRUE.");
            log.warning("The WebParser is now executing. " +
                    String.format("The file at the following location will be overwritten: %s", headerSpecLocation));
            return executeWebParser();

        } else {

            log.info("Skipping WebParser functionality.");
            return headerSpecIO.readHeaderSpecFile();

        }

    }

}
