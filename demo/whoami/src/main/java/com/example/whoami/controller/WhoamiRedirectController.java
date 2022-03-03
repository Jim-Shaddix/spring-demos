package com.example.whoami.controller;

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
public class WhoamiRedirectController {

    /**
     * redirect url that routes the user to the whoami path, and
     * sets a query parameter indicating the presence of the redirect.
     *
     * @param model allows for setting query parameters in the redirect url.
     * @return the redirect view for rerouting the user.
     */
    @RequestMapping("/")
    public ModelAndView initRediect(ModelMap model) {
        model.addAttribute("whoami-redirect", "true");
        return new ModelAndView("redirect:/whoami", model);
    }

}
