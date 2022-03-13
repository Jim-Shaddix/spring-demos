package com.example.whoami.webparser.rfcparser;

import com.example.whoami.webparser.Header;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Rfc2616Header extends Header {
    private String name;
    private String definition;
}
