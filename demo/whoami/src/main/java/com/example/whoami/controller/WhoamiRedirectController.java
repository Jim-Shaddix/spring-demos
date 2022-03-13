package com.example.whoami.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Tests all redirect responses for the Whoami app.
 */
@ConditionalOnProperty(value = "whoami.controller.enable-redirect-controller", havingValue = "true")
@Controller
@Tag(name = "Redirect-Controller", description = "Endpoints that redirect the client.")
public class WhoamiRedirectController {

    /**
     * redirects the client to the whoami path, and
     * sets a query parameter indicating the presence of the redirect.
     *
     * @param model allows for setting query parameters in the redirect url.
     * @return the redirect view for rerouting the user.
     */
    @Operation(summary = "redirects to /whoami")
    @RequestMapping("/")
    public ModelAndView initRedirect(ModelMap model) {
        model.addAttribute("whoami-redirect", "true");
        return new ModelAndView("redirect:/whoami", model);
    }

    @Operation(summary = "forwards to /whoami")
    @RequestMapping("/forward")
    public ModelAndView initForward(ModelMap model) {
        return new ModelAndView("forward:/whoami", model);
    }

}
