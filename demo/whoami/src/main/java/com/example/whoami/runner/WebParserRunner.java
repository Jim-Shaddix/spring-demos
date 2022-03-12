package com.example.whoami.runner;

import com.example.whoami.webparser.HeaderSerializer;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Log
@Service
@AllArgsConstructor
public class WebParserRunner implements CommandLineRunner {

    HeaderSerializer headerSerializer;

    @Override
    public void run(String... args) throws Exception {
        headerSerializer.serializeHeaders();
    }
}
