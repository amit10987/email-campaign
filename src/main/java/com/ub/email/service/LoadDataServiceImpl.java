package com.ub.email.service;

import com.ub.email.repository.CampaignRepository;
import com.ub.email.repository.EmailStatsRepository;
import com.ub.email.repository.EmailTemplateRepository;
import com.ub.email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadDataServiceImpl implements LoadDataService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailStatsRepository emailStatsRepository;

    @Override
    public void loadData() {
        cleanTheData();
    }

    private void cleanTheData() {
        campaignRepository.deleteAll();
        emailTemplateRepository.deleteAll();
        userRepository.deleteAll();
        emailStatsRepository.deleteAll();
    }
}
