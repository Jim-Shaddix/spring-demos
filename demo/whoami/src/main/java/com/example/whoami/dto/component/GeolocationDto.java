package com.example.whoami.dto.component;

import com.example.whoami.dto.description.BasicDescriptionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "GeoIP data from https://api.freegeoip.app/json/8.8.8.8?apikey=d6c6edf0-b4ff-11ec-aea3-b7c9e74c67cb.")
public class GeolocationDto extends BasicDescriptionDto {

    @Schema(description = "Clients IP address used for establishing a connection to this service")
    @JsonProperty("ip")
    private String ip;

    @Schema(description = "country code describing the clients ip")
    @JsonProperty("country_code")
    private String countryCode;

    @Schema(description = "country name associated with the clients ip")
    @JsonProperty("country_name")
    private String countryName;

    @Schema(description = "region code associated with the clients ip")
    @JsonProperty("region_code")
    private String regionCode;

    @Schema(description = "region name associated with the clients ip")
    @JsonProperty("region_name")
    private String regionName;

    @Schema(description = "city associated with the clients ip")
    @JsonProperty("city")
    private String city;

    @Schema(description = "zip code associated with the clients ip")
    @JsonProperty("zip_code")
    private String zipCode;

    @Schema(description = "time zone associated with the clients ip")
    @JsonProperty("time_zone")
    private String timeZone;

    @Schema(description = "latitude of the clients ip")
    @JsonProperty("latitude")
    private float latitude;

    @Schema(description = "longitude of the clients ip")
    @JsonProperty("longitude")
    private float longitude;

    @Schema(description = "metro code associated with the clients ip")
    @JsonProperty("metro_code")
    private float metroCode;
}
