package com.ub.email.controller;

import com.ub.email.entity.EmailStats;
import com.ub.email.repository.EmailStatsRepository;
import com.ub.email.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    LoadDataService loadDataService;

    @Autowired
    EmailStatsRepository emailStatsRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<EmailStats> emailStats = (List<EmailStats>) emailStatsRepository.findAll();
        model.addAttribute("stats", emailStats);
        return "home";
    }

    @PostMapping("/loadData")
    public String loadData() {
        loadDataService.loadData();
        return "home";
    }
}
