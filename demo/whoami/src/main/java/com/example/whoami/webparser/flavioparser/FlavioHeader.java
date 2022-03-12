package com.example.whoami.webparser.flavioparser;

import com.example.whoami.webparser.Header;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class FlavioHeader extends Header {
    private String name;
    private String example;
    private String definition;
    private FlavioHeaderType headerType;
}
