package com.ub.email.repository;

import com.ub.email.entity.EmailTemplate;
import org.springframework.data.repository.CrudRepository;

public interface EmailTemplateRepository extends CrudRepository<EmailTemplate, Long> {
}
