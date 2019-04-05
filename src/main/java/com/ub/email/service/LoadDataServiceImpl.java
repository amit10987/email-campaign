package com.ub.email.service;

import com.ub.email.entity.Campaign;
import com.ub.email.entity.EmailTemplate;
import com.ub.email.entity.User;
import com.ub.email.repository.CampaignRepository;
import com.ub.email.repository.EmailStatsRepository;
import com.ub.email.repository.EmailTemplateRepository;
import com.ub.email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        cleanData();
        populateCampain();
        populateEmailTemplate();
        populateUser();
    }

    private void populateUser() {
        List<User> users = new ArrayList<>();
        IntStream.rangeClosed(1,50).forEach(x -> {
            String emailId = "user" + x + "@urbanbuz.com";
            String firstName = "user" + x;
            String lastName = "ub";
            users.add(getUser(emailId, firstName, lastName));
        });
        userRepository.saveAll(users);
    }

    private User getUser(String emailId, String firstName, String lastName) {
        User user = new User();
        user.setEmailId(emailId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    private void populateEmailTemplate() {
        List<EmailTemplate> emailTemplates = new ArrayList<>();
        emailTemplates.add(getEmailTemplate("FoodyFriday", "Foody Friday", "50% discount on every item..", "Campaign0001"));
        emailTemplates.add(getEmailTemplate("PizzaCoupon", "pizza coupons", "pizza coupon code PGGFH568", "Campaign0001"));
        emailTemplates.add(getEmailTemplate("FlatFifty", "Flat Fifty ", "Use Coupon ASCVF to get flat 40% discount.", "Campaign0002"));
        emailTemplates.add(getEmailTemplate("StartShopping", "Code Gladiators 2019 is Back.", " Win upto 1 Cr worth of Prizes. Participate Now", "Campaign0002"));
        emailTemplates.add(getEmailTemplate("LuckyDay", "7th April Can Be Your Luckiest Day", "download the app and get chance to win flight ticket to Europe.", "Campaign0002"));
        emailTemplateRepository.saveAll(emailTemplates);
    }

    private EmailTemplate getEmailTemplate(String name, String subject, String body, String campaignName) {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setName(name);
        emailTemplate.setSubject(subject);
        emailTemplate.setBody(body);
        emailTemplate.setCampaignName(campaignName);
        return emailTemplate;
    }

    private void populateCampain() {
        campaignRepository.save(getCampaign("Campaign0001", "Advertisement for restaurant"));
        campaignRepository.save(getCampaign("Campaign0002", "Advertisement for online shopping"));
    }

    private Campaign getCampaign(String name, String desc){
        Campaign campaign = new Campaign();
        campaign.setName(name);
        campaign.setDescription(desc);
        return campaign;
    }

    private void cleanData() {
        campaignRepository.deleteAll();
        emailTemplateRepository.deleteAll();
        userRepository.deleteAll();
        emailStatsRepository.deleteAll();
    }
}
