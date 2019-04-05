package com.ub.email.service;

import com.ub.email.entity.EmailStats;

public interface EmailService {
    EmailStats sendEmail(Long templateId);
}
