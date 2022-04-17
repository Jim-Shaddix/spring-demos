package com.example.whoami.api;

import com.example.whoami.config.GeoIpProperties;
import com.example.whoami.dto.component.GeolocationDto;
import com.example.whoami.dto.description.BasicDescriptionDto;
import com.example.whoami.exception.InvalidApiKey;
import com.example.whoami.service.util.BasicDtoDescriptionParser;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@Service
@AllArgsConstructor
public class IpGeolocationApi {

    private final GeoIpProperties geoIpProperties;

    private BasicDtoDescriptionParser basicDtoDescriptionParser;

    private void setDescription(BasicDescriptionDto basicDescriptionDto) {
        Map<String, String> map = basicDtoDescriptionParser
                .parseDtoDescription(basicDescriptionDto.getClass());
        basicDescriptionDto.setDefinition(map);
    }

    /**
     * performs an API request to gather geolocation information for an ip.
     *
     * @param ip address that you would like to gather geolocation information for.
     * @return geolocation information that pertains to the ip address passed in.
     */
    public GeolocationDto getGeolocation(String ip) {

        String apiKey = geoIpProperties.getApiKey();

        WebClient webClient = WebClient.create();

        // setting up request
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

        String fmtErrMsg = "Geolocation API key appears to be invalid [API-Key=%s]. This appears to be the case, " +
                "because a WebClientResponseException.Unauthorized exception was thrown when " +
                "trying to access the geolocation api.";
        String errMsg = String.format(fmtErrMsg, apiKey);

        // setting blocking / error handling
        GeolocationDto geolocationDto = responseSpec.bodyToMono(GeolocationDto.class)
                .doOnError(
                        WebClientResponseException.Unauthorized.class,
                        (exception) -> {
                            throw new InvalidApiKey(apiKey, errMsg, exception);
                        }
                )
                .block();

        setDescription(geolocationDto);

        return geolocationDto;
    }

}
