package com.example.whoami.webparser.exception;

public class WebParserException extends RuntimeException {

    private final static String startErrorMsg = "Error occurred while running the WebParser! ";

    public WebParserException(String errMsg, Exception e) {
        super(startErrorMsg + errMsg, e);
    }

}
