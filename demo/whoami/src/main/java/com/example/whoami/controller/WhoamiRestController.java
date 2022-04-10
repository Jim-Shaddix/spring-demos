package com.example.whoami.controller;

import com.example.whoami.dto.HeaderSpec;
import com.example.whoami.dto.WhoamiDto;
import com.example.whoami.service.SizeSpecifiedPayloadService;
import com.example.whoami.service.WhoamiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for sending metadata pertaining to a request
 * back to user.
 */
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name = "Whoami-Rest-Controller", description = "Endpoints for sending JSON metadata back to a client describing their request.")
public class WhoamiRestController {

    private final WhoamiService whoamiService;
    private final SizeSpecifiedPayloadService sizeSpecifiedPayloadService;
    private final List<HeaderSpec> headerSpecs;

    @Operation(summary = "Returns metadata describing the users request in json.")
    @RequestMapping(value = "/whoami/**", produces = "application/json")
    public WhoamiDto whoamiApi(HttpServletRequest request) {

        WhoamiDto whoamiDto = whoamiService.parseRequestMetadata(request);

        whoamiService.logRequest(whoamiDto);

        return whoamiDto;
    }

    @Operation(summary = "Returns the value of 1 every time. This endpoint is used for benchmarking network speed.")
    @GetMapping(value = "/bench")
    public String bench() {
        return "1";
    }

    @Operation(summary = "creates a response with a size n (specified using the unit parameter). " +
            "The unit of measure, if specified, accepts the following values: KB, MB, GB. " +
            "This API is used for testing network speed.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "valid response"),
                    @ApiResponse(responseCode = "400", description = "Improper unit specification used")
            })
    @GetMapping(value = "/data")
    public String sizeSpecifiedPayload(@RequestParam(value = "unit", required = true) String unit) {
        return sizeSpecifiedPayloadService.generatePayloadFromUnitSpec(unit);
    }

    @Operation(summary = "returns a list of http headers and their descriptions")
    @GetMapping(value = "/headers", produces = "application/json")
    public List<HeaderSpec> headerSpecsPayload() {
        return headerSpecs;
    }

}
