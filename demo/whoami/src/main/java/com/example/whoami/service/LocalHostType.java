package com.example.whoami.service;

public enum LocalHostType {

    LOCALHOST ("localhost", "local"),
    IPV4_LOCAL ("127.0.0.1", "ipv4"),
    IPV6_LOCAL ("[::1]", "ipv6");

    private String host;
    private String name;

    LocalHostType(String host, String name) {
        this.host = host;
        this.name = name;
    }

    public static boolean isLocalHost(String host) {
        if (host.equals(LOCALHOST.host) ||
                host.equals(IPV4_LOCAL.host) ||
                host.equals(IPV6_LOCAL.host)) {
            return true;
        }
        return false;
    }

}
