package com.ub.email.service;

import com.ub.email.entity.EmailStats;
import com.ub.email.repository.EmailStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailStatsServiceImpl implements EmailStatsService {

    @Autowired
    EmailStatsRepository emailStatsRepository;

    @Override
    public void save(EmailStats stats) {
        emailStatsRepository.save(stats);
    }
}
