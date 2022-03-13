package com.example.whoami.controller;

import com.example.whoami.dto.WhoamiDto;
import com.example.whoami.service.SizeSpecifiedPayloadService;
import com.example.whoami.service.WhoamiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for sending metadata pertaining to a request
 * back to user.
 */
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name = "Whoami-Rest-Controller", description = "Endpoints for sending metadata back to a client describing their request.")
public class WhoamiRestController {

    private ObjectMapper objectMapper;
    private WhoamiService whoamiService;
    private SizeSpecifiedPayloadService sizeSpecifiedPayloadService;

    /**
     * This endpoint displays metadata describing the users request
     * back to user in the form of raw json.
     *
     * @param request the http request received from the user.
     * @return raw json representation of the requests' metadata.
     */
    @Operation(summary = "Returns metadata describing the users request in json.")
    @RequestMapping(value = "/whoami/**", produces = "application/json")
    public String whoamiApi(HttpServletRequest request) throws JsonProcessingException {

        WhoamiDto whoamiDto = whoamiService.parseRequestMetadata(request);

        whoamiService.logRequest(whoamiDto);

        String whoamiJson = objectMapper.writeValueAsString(whoamiDto);

        return whoamiJson;
    }

    @Operation(summary = "Returns the value of 1 every time. This endpoint is used for benchmarking network speed.")
    @GetMapping(value = "/bench")
    public String bench() {
        return "1";
    }

    @Operation(summary = "creates a response with a size n (specified using the unit parameter). " +
            "The unit of measure, if specified, accepts the following values: KB, MB, GB. " +
            "This API is used for testing network speed.")
    @GetMapping(value = "/data")
    public String sizeSpecifiedPayload(@RequestParam(value = "unit", required = true) String unit) {
        return sizeSpecifiedPayloadService.generatePayloadFromUnitSpec(unit);
    }

}
