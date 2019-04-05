package com.ub.email.controller;

import com.ub.email.entity.EmailStats;
import com.ub.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(HttpServletRequest request, Model model) {
        Long templateId = Long.parseLong(request.getParameter("emailTemplate.id"));
        EmailStats stats = emailService.sendEmail(templateId);
        model.addAttribute("stats", stats);
        return "redirect:/emailTemplate";
    }
}
