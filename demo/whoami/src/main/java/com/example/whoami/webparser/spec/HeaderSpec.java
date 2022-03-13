package com.example.whoami.webparser.spec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
@Setter
@Getter
public class HeaderSpec {

    @JsonProperty("name")
    String name;

    @Nullable
    @JsonProperty("example")
    String example;

    @Nullable
    @JsonProperty("short-description")
    String shortDescription;

    @Nullable
    @JsonProperty("long-description")
    String longDescription;

    @Nullable
    @JsonProperty("type")
    String type;

    public boolean equals(String headerName) {
        return headerName.equals(name);
    }

    @Override
    public String toString() {

        int abbreviatedLength = 10;
        String longDesc = null;
        if (longDescription != null) {
            if (longDescription.length() >= abbreviatedLength) {
                longDesc = longDescription.substring(0, abbreviatedLength).trim() + " ...";
            } else {
                longDesc = longDescription.trim();
            }
        }

        return "HeaderSpec{" +
                "name='" + name + '\'' +
                ", example='" + example + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + String.valueOf(longDesc) + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
