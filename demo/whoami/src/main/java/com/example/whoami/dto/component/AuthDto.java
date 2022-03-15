package com.example.whoami.dto.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
@Schema(description = "Authentication information for the client")
public class AuthDto {

    @Schema(description = "authentication type")
    @JsonProperty("auth-type")
    private String authType;

    @Schema(description = "remote user")
    @JsonProperty("remoteUser")
    private String remoteUser;

    @Schema(description = "user principal")
    @JsonProperty("user-principal")
    private String userPrincipal;

    @Schema(description = "definitions of fields in this object")
    @JsonProperty("definitions")
    Map<String, String> definitions;
}
