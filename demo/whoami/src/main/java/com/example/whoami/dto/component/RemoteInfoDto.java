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
@Schema(description = "Information that describes the remote client! That's you!")
public class RemoteInfoDto extends BasicDescriptionDto {

    @Schema(description = "The IP address associated with the client.")
    @JsonProperty("request-address")
    private String requestAddress;

    @Schema(description = "The IP address associated with the client.")
    @JsonProperty("request-host")
    private String requestHost;

    @Schema(description = "The port the client used to send a request to this service.")
    @JsonProperty("request-port")
    private String requestPort;

    @Schema(description = "Request Host and Port that uniquely identifies the application on the last proxy that forwarded the clients request.")
    @JsonProperty("request-socket")
    private String requestSocket;

}
