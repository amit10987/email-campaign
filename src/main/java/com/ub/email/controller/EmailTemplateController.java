package com.ub.email.controller;

import com.ub.email.entity.Campaign;
import com.ub.email.entity.EmailTemplate;
import com.ub.email.repository.CampaignRepository;
import com.ub.email.repository.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmailTemplateController {

    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @GetMapping("/emailTemplate")
    public String emailTemplate(Model model) {
        List<EmailTemplate> emailTemplates = (List<EmailTemplate>) emailTemplateRepository.findAll();
        List<Campaign> campaigns = (List<Campaign>) campaignRepository.findAll();
        model.addAttribute("emailTemplates", emailTemplates);
        model.addAttribute("campaigns", campaigns);
        return "emailTemplate";
    }

    @PostMapping("/emailTemplate/create")
    public String create(@ModelAttribute EmailTemplate emailTemplate, Model model) {
        emailTemplateRepository.save(emailTemplate);
        return "redirect:/emailTemplate";
    }

    @PostMapping("/emailTemplate/delete")
    public String delete(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("email.id"));
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setId(id);
        emailTemplateRepository.delete(emailTemplate);
        return "redirect:/emailTemplate";
    }
}
