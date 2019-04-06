package com.ub.email.service;

import com.ub.email.entity.EmailStats;
import com.ub.email.repository.EmailStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    EmailStatsRepository emailStatsRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void pushAllStats() {
        List<EmailStats> emailStats = (List<EmailStats>) emailStatsRepository.findAll();
        simpMessagingTemplate.convertAndSend("/topic/homeStats", emailStats);
    }

    @Override
    public void pushStats(String uuid) {
        EmailStats stats = emailStatsRepository.findByUuid(uuid);
        simpMessagingTemplate.convertAndSend("/topic/stats", stats);
    }
}
