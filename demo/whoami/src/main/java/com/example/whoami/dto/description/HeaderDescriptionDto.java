package com.example.whoami.dto.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class HeaderDescriptionDto {

    @Schema(description = "either a Request or Response header")
    @JsonProperty("type")
    private String type;

    @Schema(description = "abbreviate definition of the header")
    @JsonProperty("description")
    private String description;

    @Schema(description = "link to the full definition with examples")
    @JsonProperty("link")
    private String link;

}
