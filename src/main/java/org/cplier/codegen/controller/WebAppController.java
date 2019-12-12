package org.cplier.codegen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebAppController {
    private String appMode;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}