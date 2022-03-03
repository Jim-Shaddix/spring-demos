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
 * Handles all error responses for the Whoami app.
 */
@ConditionalOnProperty(value = "whoami.controller.enable-error-controller", havingValue = "true")
@Controller
@Log
public class WhoamiErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {

            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                log.info("404 Error Received from URI: " + request.getRequestURI() + ".");
                return "error/404";
            }

            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/5xx";
            }

        }

        // may want to change this default to a different error
        // page in the future.
        return "error/5xx";
    }

}
