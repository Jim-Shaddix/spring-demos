package com.example.whoami.service;

import com.example.whoami.dto.component.GeolocationDto;
import com.example.whoami.config.GeoIpProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class GeoIpService {

    private final GeoIpProperties geoIpProperties;

    /**
     * performs an API request to gather geolocation information for an ip.
     *
     * @param ip address that you would like to gather geolocation information for.
     * @return geolocation information that pertains to the ip address passed in.
     */
    public @Nullable
    GeolocationDto getGeoIp(String ip) {

        String apiKey = geoIpProperties.getApiKey();

        WebClient webClient = WebClient.create();

        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .scheme("https")
                    .host("api.freegeoip.app")
                    .pathSegment("json", ip)
                    .queryParam("apikey", apiKey)
                    .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

        GeolocationDto geoIp = responseSpec.bodyToMono(GeolocationDto.class).block();

        return geoIp;
    }

}
