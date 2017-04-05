package com.risingapp.likeit.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zinoviyzubko on 05.04.17.
 */
@Controller
public class FrontendController {

    @RequestMapping(value = "/home")
    public String getIndex() {
        return "index.html";
    }

    @RequestMapping(value = "/login")
    public String getWelcome() {
        return "welcome.html";
    }
}
