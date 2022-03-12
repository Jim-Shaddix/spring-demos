package com.example.whoami.webparser.spec;

import lombok.Data;

@Data
public class HeaderSpec {

    String name;
    String example;
    String shortDescription;
    String longDescription;

    public boolean equals(String headerName) {
        return headerName.equals(name);
    }

}
