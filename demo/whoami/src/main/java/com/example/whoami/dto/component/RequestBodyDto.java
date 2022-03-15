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
@Schema(description = "HTTP Request body")
public class RequestBodyDto extends BasicDescriptionDto {

    @Schema(description = "HTTP Request Body that was received.")
    @JsonProperty("content")
    private String content;

}
