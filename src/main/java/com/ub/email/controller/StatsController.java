package com.ub.email.controller;

import com.ub.email.entity.EmailStats;
import com.ub.email.repository.EmailStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Email;
import java.util.List;

@Controller
public class StatsController {

    @Autowired
    EmailStatsRepository emailStatsRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/stat")
    public String stats(Model model) {
        List<EmailStats> stats = (List<EmailStats>) emailStatsRepository.findAll();
        model.addAttribute("stats", stats);
        return "stats";
    }

    @MessageMapping("/stats")
    public void pushStats(String uuid) {
        EmailStats stats = emailStatsRepository.findByUuid(uuid);
        simpMessagingTemplate.convertAndSend("/topic/stats", stats);
    }
}
