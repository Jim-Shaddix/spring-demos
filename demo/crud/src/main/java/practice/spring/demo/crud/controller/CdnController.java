package practice.spring.demo.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CdnController {

    @RequestMapping("/")
    public String getHomePage() {
        return "init";
    }

}
