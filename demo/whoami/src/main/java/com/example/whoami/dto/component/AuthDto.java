package com.example.whoami.dto.component;

import com.example.whoami.dto.description.BasicDescriptionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Schema(description = "Authentication information for the client")
public class AuthDto extends BasicDescriptionDto {

    @Schema(description = "authentication type")
    @JsonProperty("auth-type")
    private String authType;

    @Schema(description = "remote user")
    @JsonProperty("remote-user")
    private String remoteUser;

    @Schema(description = "user principal")
    @JsonProperty("user-principal")
    private String userPrincipal;

}
