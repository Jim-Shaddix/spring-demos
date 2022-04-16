package com.example.whoami.service;

public enum HostType {
    Subdomain_Domain_Tld ("subdomain.domain.tld"),
    Domain_Tld ("domain.tld"),
    IP ("ip"),
    LOCALHOST ("local");

    private String formatNumberString;

    HostType(String formatNumberString) {
        this.formatNumberString = formatNumberString;
    }

    @Override
    public String toString() {
        return formatNumberString;
    }
}
