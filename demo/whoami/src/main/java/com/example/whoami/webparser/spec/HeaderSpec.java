package com.example.whoami.webparser.spec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
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

    @Override
    public String toString() {

        int abbreviatedLength = 10;
        String longDesc;
        if (longDescription.length() >= abbreviatedLength) {
            longDesc = longDescription.substring(0, abbreviatedLength).trim() + " ...";
        } else {
            longDesc = longDescription.trim();
        }

        return "HeaderSpec{" +
                "name='" + name + '\'' +
                ", example='" + example + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDesc + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
