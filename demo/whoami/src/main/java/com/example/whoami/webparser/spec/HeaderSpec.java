package com.example.whoami.webparser.spec;

import lombok.Data;
import lombok.ToString;

@Data
public class HeaderSpec {

    String name;
    String example;
    String shortDescription;
    String longDescription;
    String type;

    public boolean equals(String headerName) {
        return headerName.equals(name);
    }

}
