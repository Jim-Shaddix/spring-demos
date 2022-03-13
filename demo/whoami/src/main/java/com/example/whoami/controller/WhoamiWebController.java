package com.example.whoami.controller;

import com.example.whoami.dto.WhoamiDto;
import com.example.whoami.service.WhoamiService;
import com.example.whoami.webparser.spec.HeaderSpec;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@Tag(name = "Whoami-Web-Controller",
     description = "Endpoints for sending metadata back to a client describing their request.")
public class WhoamiWebController {

    private WhoamiService whoamiService;

    private List<HeaderSpec> headerSpecs;

    /**
     * This endpoint displays metadata describing the users request
     * back to user in the form of an html file.
     *
     * @param model contains the html template attributes.
     * @param request the http request received from the user.
     * @return the formatted html template with the users' metadata displayed in json.
     */
    @Operation(summary = "Displays metadata describing the users request in html.")
    @RequestMapping("/whoami/**")
    public String whoamiHtml(Model model, HttpServletRequest request) {

        WhoamiDto whoamiDto = whoamiService.parseRequestMetadata(request);

        whoamiService.logRequest(whoamiDto);

        model.addAttribute("jsonBlob", whoamiDto);

        return "whoami";
    }

    @Operation(summary = "Displays descriptions of possible http headers")
    @GetMapping("/header")
    public String headerView(Model model, HttpServletRequest request) {
        model.addAttribute("jsonBlob", headerSpecs);
        return "header-table";
    }

}
