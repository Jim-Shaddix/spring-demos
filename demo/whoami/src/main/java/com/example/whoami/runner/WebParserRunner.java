package com.example.whoami.runner;

import com.example.whoami.webparser.HeaderSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

@Log
@Service
@RequiredArgsConstructor
public class WebParserRunner implements CommandLineRunner {

    private final HeaderSerializer headerSerializer;

    @Value("${whoami.webparser.force-parser-execution}")
    private boolean forceExecution;

    @Value("${whoami.webparser.http-header-spec-location}")
    private String headerSpecLocation;

    private boolean jsonHeaderSpecFileExists() {
        return Paths.get(headerSpecLocation).toFile().exists();
    }

    private void executeWebParser() throws IOException {
        log.info("Fetching definitions of http headers ...");
        headerSerializer.serializeHeaders();
        log.info("Finished fetching, parsing, " +
                "and serializing all of the http header information.");
    }

    @Override
    public void run(String... args) throws Exception {
        if (!jsonHeaderSpecFileExists()) {
            log.warning("The Header-Spec-File does not exist! " +
                    "Could not find the following file: " + headerSpecLocation);
            log.warning("Because the spec file could not be found, the webparser is now executing.");
            executeWebParser();
        } else if (forceExecution) {
            log.warning("The WebParser Force Execution Setting is set to TRUE.");
            log.warning("The WebParser is now executing. " +
                    String.format("The file at the following location will be overwritten", headerSpecLocation));
            executeWebParser();
        } else {
            log.info("Skipping WebParser functionality.");
        }

    }

}
