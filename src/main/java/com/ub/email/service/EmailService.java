package com.ub.email.service;

import com.ub.email.entity.EmailStats;

/**
 *  That Interface expose email functionality for email campaign
 */
public interface EmailService {

    /**
     * provides functionality to send email based on template id
     *
     * @param templateId That could be any template id exist in DB
     * @return returns email statistic like total clicked, opened and delivered email
     */
    EmailStats sendEmail(Long templateId);
}
