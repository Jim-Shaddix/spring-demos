package com.example.whoami.dto.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public abstract class HeaderDescriptionDto {

    @Schema(description = "either a Request or Response header")
    @JsonProperty("type")
    private String type;

    @Schema(description = "abbreviate definition of the header")
    @JsonProperty("short-definition")
    private String shortDefinition;

    @Schema(description = "example value of the header")
    @JsonProperty("example")
    private String example;

    @Schema(description = "Full definition of the header from the RFC reference")
    @JsonProperty("long-definition")
    private String longDefinition;

}
