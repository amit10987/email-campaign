package com.ub.email.controller;

import com.ub.email.entity.EmailStats;
import com.ub.email.repository.EmailStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatsController {

    @Autowired
    EmailStatsRepository emailStatsRepository;

    @GetMapping("/stat")
    public String stats(Model model){
        List<EmailStats> stats = (List<EmailStats>) emailStatsRepository.findAll();
        model.addAttribute("stats", stats);
        return "stats";
    }
}
