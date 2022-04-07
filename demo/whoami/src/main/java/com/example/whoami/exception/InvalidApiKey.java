package com.example.whoami.exception;

public class InvalidApiKey extends RuntimeException {

    private String apiKey;

    public InvalidApiKey(String apiKey, String msg) {
        super(msg);
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

}
