package com.ub.email.service;

import com.ub.email.entity.EmailStats;
import com.ub.email.entity.EmailTemplate;
import com.ub.email.entity.User;
import com.ub.email.repository.EmailTemplateRepository;
import com.ub.email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailStatsService emailStatsService;

    @Override
    public EmailStats sendEmail(Long templateId) {
        EmailTemplate template = emailTemplateRepository.findById(templateId).orElse(getDefaultTemplate());
        List<SimpleMailMessage> simpleMailMessagess = getSimpleMailMessage(template);
        EmailStats stats = sendMailAndGetEmailStats(simpleMailMessagess);
        stats.setCampaignName(template.getCampaignName());
        stats.setTemplateName(template.getName());
        emailStatsService.save(stats);
        return stats;
    }

    private EmailStats sendMailAndGetEmailStats(List<SimpleMailMessage> simpleMailMessagess) {
        EmailStats stats = new EmailStats();
        stats.setTotalSent(new Long(simpleMailMessagess.size()));
        for (SimpleMailMessage msg : simpleMailMessagess) {
            try {
                javaMailSender.send(msg);
            } catch (Exception ex) {
                stats.incrementTotalFailed();
                System.out.println("email sending failed for user" + msg.getTo());
            }
        }
        Long totalDelivered = stats.getTotalSent() - stats.getTotalFailed();
        stats.setTotalDelivered(totalDelivered);
        return stats;
    }


    private EmailTemplate getDefaultTemplate() {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject("test subject");
        emailTemplate.setBody("test body");
        return emailTemplate;
    }

    private List<SimpleMailMessage> getSimpleMailMessage(EmailTemplate emailTemplate) {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(prepareMailMessage(emailTemplate)).collect(Collectors.toList());
    }

    /**
     * @param emailTemplate
     * @return
     */
    private Function<User, SimpleMailMessage> prepareMailMessage(EmailTemplate emailTemplate) {
        return user -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("amit@localhost");
            simpleMailMessage.setTo(user.getEmailId());
            simpleMailMessage.setSubject(emailTemplate.getSubject());
            simpleMailMessage.setText(emailTemplate.getBody());
            return simpleMailMessage;
        };
    }
}
