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
@Schema(description = "This Dto describes the Request headers sent to the server.")
public class RequestMethodDto extends BasicDescriptionDto {

    @Schema(description = "HTTP Method used for specifying " +
            "what server side operation should be performed")
    @JsonProperty("method")
    private String requestMethod;

}
