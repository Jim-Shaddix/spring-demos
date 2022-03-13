package com.example.whoami.runner;

import com.example.whoami.webparser.spec.HeaderSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@RequiredArgsConstructor
public class WebParserRunner implements CommandLineRunner {

    private final List<HeaderSpec> headerSpecs;

    @Override
    public void run(String... args) throws Exception {
        for (HeaderSpec headerSpec: headerSpecs) {
            System.out.println(headerSpec.toString());
        }
    }

}
