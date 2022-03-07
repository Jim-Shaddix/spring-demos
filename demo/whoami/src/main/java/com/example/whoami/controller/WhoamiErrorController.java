package com.example.whoami.controller;

import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * This controller handles all the error responses.
 */
@ConditionalOnProperty(value = "whoami.controller.enable-error-controller", havingValue = "true")
@Controller
@Log
public class WhoamiErrorController implements ErrorController {

    private final static String DEFAULT_ERROR_PAGE = "error/5xx";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {

            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                log.warning("404 Error received from URI: " + request.getRequestURI() + ".");
                return "error/404";
            }

            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                log.warning("500 Error received from URI: " + request.getRequestURI() + ".");
                return "error/5xx";
            }

        }

        return DEFAULT_ERROR_PAGE;
    }

}
