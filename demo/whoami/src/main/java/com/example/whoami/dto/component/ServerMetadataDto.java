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
@Schema(description = "contains all of the metadata associated with the server.")
public class ServerMetadataDto extends BasicDescriptionDto {

    @Schema(description = "hostname of the server, or the containerID if this " +
            "image is running in a docker image.")
    @JsonProperty("device-hostname")
    private String deviceHostName;

    @Schema(description = "ip addresses that can be used for contacting the whoami server")
    @JsonProperty("all-ips")
    private String hostIps;

    @Schema(description = "hostnames that can be used for contacting the whoami server")
    @JsonProperty("all-hostnames")
    private String hostnames;
}
