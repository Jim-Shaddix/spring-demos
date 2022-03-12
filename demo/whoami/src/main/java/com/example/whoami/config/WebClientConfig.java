package com.example.whoami.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configures webclient for the web-parser.
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {

        // 16 Bytes
        final int size = 16 * 1024 * 1024;

        // setting max byte return size in strategy
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        // returning a webclient with the configured strategy
        return WebClient.builder()
                .exchangeStrategies(strategies)
                .build();
    }

}
