package com.ub.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailTemplateController {

    @GetMapping("/emailTemplate")
    public String emailTemplate() {
        return "emailTemplate";
    }
}
