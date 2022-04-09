package com.example.whoami.service;

import com.example.whoami.api.IpGeolocationApi;
import com.example.whoami.config.GeoIpProperties;
import com.example.whoami.dto.component.GeolocationDto;
import com.example.whoami.exception.InvalidApiKey;
import com.example.whoami.parser.BasicDtoDescriptionParser;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class IpDescriptionService {

    private final IpGeolocationApi ipGeolocationApi;
    private final GeoIpProperties geoIpProperties;
    private final BasicDtoDescriptionParser basicDtoDescriptionParser;

    private InetAddress[] getAddresses(String host) {

        InetAddress[] addresses;

        try {
            InetAddress address = InetAddress.getByName(host);
            addresses = InetAddress.getAllByName(host);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unexpected error occurred when host ip information", e);
        }

        return addresses;
    }

    /**
     * @return a comma delimited list of valid ip addresses that could have have been used for contacting this server.
     */
    public String parseIps(String host) {
        return Arrays.stream(getAddresses(host))
                .map(InetAddress::getHostAddress)
                .collect(Collectors.joining(","));
    }

    /**
     * @return a comma delimited list of valid ip addresses that could have have been used for contacting this server.
     */
    public String parseHostNames(String host) {
        return Arrays.stream(getAddresses(host))
                .map(InetAddress::getHostName)
                .collect(Collectors.joining(","));
    }

    /**
     * @param host this string could be a hostName, ipv4 address, or ipv6 address
     * @return a String that characterizes the type of IP Address.
     */
    public String parseIpType(String host) {

        String ipType = null;

        try {

            // InetAddress.getByName will accept a hostname, IPv4, or IPv6, therefore, this should never fail.
            InetAddress address = InetAddress.getByName(host);

            // setting IP type
            if (address instanceof Inet4Address) {
                ipType = "ipv4";
            } else if (address instanceof Inet6Address) {
                ipType = "ipv6";
            }

        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred when parsing remote users hostname/ip information");
        }

        return null;
    }

    public GeolocationDto getGeolocation(String ip) {
        if (ip.equals("127.0.0.1") || ip.equals("localhost")) {
            return getGeolocationForLocalHost();
        } else {
            return getGeolocationForNonLocalHost(ip);
        }
    }

    private GeolocationDto getGeolocationForLocalHost() {
        GeolocationDto geolocationDto = new GeolocationDto();
        basicDtoDescriptionParser.setDescription(geolocationDto);
        return geolocationDto;
    }

    private GeolocationDto getGeolocationForNonLocalHost(String ip) {

        GeolocationDto geolocationDto = new GeolocationDto();

        if (!geoIpProperties.getApiKey().equals("empty")) {
            try {
                geolocationDto = ipGeolocationApi.getGeolocation(ip);
            } catch (InvalidApiKey e) {
                log.warning(e.getMessage());
                log.info("Skipping setting geolocation properties, because the apikey appears to be invalid.");
            }
        } else {
            log.warning("Parser properties are set to parse the geolocation, but NO geolocation API-key could be found. " +
                    "Application is skipping parsing Geo Location data.");
        }

        return geolocationDto;
    }

}
