package com.example.whoami.dto.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Schema(description = "Dto field definitions")
public abstract class BasicDescriptionDto {

    @Schema(description = "definition of a field in a dto. " +
            "(All keys come in the form \"field-description\")")
    @JsonProperty("description")
    private Map<String, String> definition;

}
