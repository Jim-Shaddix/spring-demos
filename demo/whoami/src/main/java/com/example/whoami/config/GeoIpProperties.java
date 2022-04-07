package com.example.whoami.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

@Setter
@Getter
@ToString
@ConfigurationProperties("whoami.geoip")
public class GeoIpProperties {

    /**
     * API Key for accessing Geo Location Services
     */
    @Nullable
    private String apiKey;
}
