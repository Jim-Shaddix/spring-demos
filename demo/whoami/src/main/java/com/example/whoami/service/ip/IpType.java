package com.example.whoami.service.ip;

public enum IpType {

    IPV4 ("ipv4"),
    IPV6 ("ipv6");

    private String ipTypeString;

    IpType(String ipTypeString) {
        this.ipTypeString = ipTypeString;
    }

    @Override
    public String toString() {
        return ipTypeString;
    }

}
