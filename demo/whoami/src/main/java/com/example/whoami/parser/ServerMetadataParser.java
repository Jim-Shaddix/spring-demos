package com.example.whoami.parser;

import com.example.whoami.dto.component.ServerMetadataDto;
import com.example.whoami.dto.description.BasicDescriptionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * parses metadata describing the server or docker image
 * that is running this application.
 */
@Service
@AllArgsConstructor
public class ServerMetadataParser {

    private BasicDtoDescriptionParser basicDtoDescriptionParser;

    public ServerMetadataDto parseServerMetaData() {
        ServerMetadataDto serverMetadataDto = new ServerMetadataDto();
        serverMetadataDto.setHostname(parseHostName());
        setDescription(serverMetadataDto);
        return serverMetadataDto;
    }

    /**
     * parses the hostname of the machine running the whoami application.
     * In the event where this application is running in a docker container,
     * then method will return the containerID.
     * The following post gives details on how to acquire a hostname
     * in java: https://stackoverflow.com/questions/7348711/recommended-way-to-get-hostname-in-java
     *
     * @return hostname of the machine running this application.
     */
    private String parseHostName() {

        String hostname;

        // using unix hostname to get result
        String unixHost = System.getenv("HOSTNAME");
        if (unixHost != null) {
            return unixHost;
        }

        // using windows environment variable to get hostname
        String windowsHost = System.getenv("COMPUTERNAME");
        if (windowsHost != null) {
            return windowsHost;
        }

        // using inet to get hostname
        // this is useful for determining the hostname on Windows systems,
        // in the event that the COMPUTERNAME environment variable is not set.
        try {
            String inetHost = InetAddress.getLocalHost().getHostName();
            if (inetHost.length() != 0) {
                return inetHost;
            }
        } catch (UnknownHostException e) {
        }

        return null;
    }

    private void setDescription(BasicDescriptionDto basicDescriptionDto) {
        Map<String, String> map = basicDtoDescriptionParser
                .parseDtoDescription(basicDescriptionDto.getClass());
        basicDescriptionDto.setDefinition(map);
    }

}