package com.example.whoami.controller;

import com.example.whoami.dto.HeaderSpec;
import com.example.whoami.dto.WhoamiDto;
import com.example.whoami.service.WhoamiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@Tag(name = "Whoami-Web-Controller",
        description = "Endpoints that return HTML with content describing the client's request.")
public class WhoamiWebController {

    private WhoamiService whoamiService;

    private List<HeaderSpec> headerSpecs;

    @Operation(summary = "Displays metadata describing the users request in html.")
    @GetMapping("/whoami-json-display/**")
    public String whoamiHtml(Model model, HttpServletRequest request) {
        WhoamiDto whoamiDto = whoamiService.parseRequestMetadata(request);
        whoamiService.logRequest(whoamiDto);
        model.addAttribute("jsonBlob", whoamiDto);
        return "whoami-json-display";
    }

    @Operation(summary = "Displays descriptions of possible http headers.")
    @GetMapping("/headers")
    public String headerView(Model model, HttpServletRequest request) {
        model.addAttribute("jsonBlob", headerSpecs);
        return "headers";
    }

    @Operation(summary = "Displays descriptions of all of the whoami information.")
    @GetMapping("/whoami/**")
    public String whoamiTableView(Model model, HttpServletRequest request) {
        WhoamiDto whoamiDto = whoamiService.parseRequestMetadata(request);
        model.addAttribute("jsonBlob", whoamiDto);
        return "whoami";
    }

}
