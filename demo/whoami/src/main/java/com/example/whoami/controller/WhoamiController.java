package com.example.whoami.controller;

import com.example.whoami.service.WhoamiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Controller for sending metadata pertaining to a request
 * back to user.
 */
@Controller
@AllArgsConstructor
public class WhoamiController {

    private ObjectMapper objectMapper;
    private WhoamiService whoamiService;

    /**
     * This endpoint displays metadata describing the users request
     * back to user in the form of raw json.
     *
     * @param request the http request received from the user.
     * @return raw json representation of the requests' metadata.
     */
    @RequestMapping(value = "/api/whoami/**", produces = "application/json")
    @ResponseBody
    public String whoamiApi(HttpServletRequest request) {

        Map<String, Object> whoamiMap = whoamiService.parseRequestMetadata(request);

        whoamiService.logRequest(whoamiMap);

        String whoamiJson = "";

        try {
            whoamiJson = objectMapper.writeValueAsString(whoamiMap);
        } catch (Exception e) {
            System.err.println("An error occurred while attempting to create json object.");
            e.printStackTrace();
        }

        return whoamiJson;
    }

    /**
     * This endpoint displays metadata describing the users request
     * back to user in the form of an html file.
     *
     * @param model contains the html template attributes.
     * @param request the http request received from the user.
     * @return the formatted html template with the users' metadata displayed in json.
     */
    @RequestMapping("/whoami/**")
    public String whoamiHtml(Model model, HttpServletRequest request) {

        Map<String, Object> whoamiMap = whoamiService.parseRequestMetadata(request);

        whoamiService.logRequest(whoamiMap);

        model.addAttribute("jsonBlob", whoamiMap);

        return "whoami";
    }

}
