package com.ub.email.controller;

import com.ub.email.entity.Campaign;
import com.ub.email.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CampaignController {

    @Autowired
    CampaignRepository repository;

    @GetMapping("/campaign")
    public String homePage(Model model)   {
        List<Campaign> campaigns = (List<Campaign>) repository.findAll();
        model.addAttribute("campaigns", campaigns);
        return "campaign";
    }

    @PostMapping("/campaign/create")
    public String create(@ModelAttribute Campaign campaign, Model model) {
        repository.save(campaign);
        return "redirect:/campaign";
    }

    @PostMapping("/campaign/delete")
    public String delete(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("campaign.id"));
        Campaign campaign = new Campaign();
        campaign.setId(id);
        repository.delete(campaign);
        return "redirect:/campaign";
    }
}
