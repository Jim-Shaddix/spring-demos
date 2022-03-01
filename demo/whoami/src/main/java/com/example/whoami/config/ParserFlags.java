package com.example.whoami.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@ToString
@Component
public class ParserFlags {

    @Value("${whoami.parse-body:true}")
    private boolean body;

    @Value("${whoami.parse-headers:true}")
    private boolean header;

    @Value("${whoami.parse-url-parts:true}")
    private boolean urlParts;

    @Value("${whoami.parse-remote-info:true}")
    private boolean remoteInfo;

    @Value("${whoami.parse-auth-info:true}")
    private boolean authInfo;

}