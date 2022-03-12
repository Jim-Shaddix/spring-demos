package com.example.whoami.webparser.spec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeaderSpec {

    @JsonProperty("name")
    String name;

    @JsonProperty("example")
    String example;

    @JsonProperty("short-description")
    String shortDescription;

    @JsonProperty("long-description")
    String longDescription;

    @JsonProperty("type")
    String type;

    public boolean equals(String headerName) {
        return headerName.equals(name);
    }

}
