package com.example.whoami.controller;

import com.example.whoami.service.WhoamiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Controller for sending metadata pertaining to a request
 * back to user.
 */
@Controller
@AllArgsConstructor
@Tag(name = "Whoami-Rest-Controller", description = "Endpoints for sending metadata back to a client describing their request.")
public class WhoamiRestController {

    private ObjectMapper objectMapper;
    private WhoamiService whoamiService;

    /**
     * This endpoint displays metadata describing the users request
     * back to user in the form of raw json.
     *
     * @param request the http request received from the user.
     * @return raw json representation of the requests' metadata.
     */
    @Operation(summary = "Returns metadata describing the users request in json.")
    @ResponseBody
    @RequestMapping(value = "/api/v1/whoami/**", produces = "application/json")
    public String whoamiApi(HttpServletRequest request) throws JsonProcessingException {

        Map<String, Object> whoamiMap = whoamiService.parseRequestMetadata(request);

        whoamiService.logRequest(whoamiMap);

        String whoamiJson = objectMapper.writeValueAsString(whoamiMap);

        return whoamiJson;
    }

}
