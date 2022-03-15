package com.example.whoami.dto.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HeaderDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;

    @JsonProperty("type")
    private String type;

    @JsonProperty("short-definition")
    private String shortDefinition;

    @JsonProperty("example")
    private String example;

    @JsonProperty("long-definition")
    private String longDefinition;

}
