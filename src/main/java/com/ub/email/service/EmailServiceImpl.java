package com.ub.email.service;

import com.ub.email.entity.EmailStats;
import com.ub.email.entity.EmailTemplate;
import com.ub.email.entity.User;
import com.ub.email.repository.EmailTemplateRepository;
import com.ub.email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.UUID;
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
        UUID uuid = UUID.randomUUID();
        List<MimeMessage> mimeMessages = getSimpleMailMessage(template, uuid.toString());
        EmailStats stats = sendMailAndGetEmailStats(mimeMessages);
        stats.setCampaignName(template.getCampaignName());
        stats.setTemplateName(template.getName());
        stats.setUuid(uuid.toString());
        emailStatsService.save(stats);
        return stats;
    }

    private EmailStats sendMailAndGetEmailStats(List<MimeMessage> mimeMessages) {
        EmailStats stats = new EmailStats();
        stats.setTotalSent(new Long(mimeMessages.size()));
        for (MimeMessage msg : mimeMessages) {
            try {
                javaMailSender.send(msg);
            } catch (Exception ex) {
                stats.incrementTotalFailed();
                System.out.println("email sending failed for user" + msg);
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

    private List<MimeMessage> getSimpleMailMessage(EmailTemplate emailTemplate, String uuid) {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(prepareMimeMailMessage(emailTemplate, uuid)).collect(Collectors.toList());
    }

    private Function<User, MimeMessage> prepareMimeMailMessage(EmailTemplate emailTemplate, String uuid) {
        return user -> {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom("amit@localhost");
                helper.setTo(user.getEmailId());
                helper.setSubject(emailTemplate.getSubject());
                String mailBody = prepareMailBody(emailTemplate, uuid);
                helper.setText(mailBody, true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return mimeMessage;
        };
    }

    private String prepareMailBody(EmailTemplate emailTemplate, String uuid) {
        StringBuilder mailBuilder = new StringBuilder();
        mailBuilder.append("<img src=http://localhost:8080/offer/")
                .append(uuid).append("/> <br/><br/>")
                .append(emailTemplate.getBody())
                .append("<br/><br/><a href=http://localhost:8080/click/")
                .append(uuid)
                .append("> Click here for more details</a>");
        return mailBuilder.toString();
    }
}
