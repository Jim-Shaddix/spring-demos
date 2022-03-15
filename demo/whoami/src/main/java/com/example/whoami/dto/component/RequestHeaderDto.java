package com.example.whoami.dto.component;

import com.example.whoami.dto.description.HeaderDescriptionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "This Dto describes the Request headers sent to the server.")
public class RequestHeaderDto extends HeaderDescriptionDto {

    @Schema(description = "Name of the request header.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Value of the request header.")
    @JsonProperty("value")
    private String value;

}
