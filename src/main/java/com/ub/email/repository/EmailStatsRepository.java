package com.ub.email.repository;

import com.ub.email.entity.EmailStats;
import org.springframework.data.repository.CrudRepository;

public interface EmailStatsRepository extends CrudRepository<EmailStats, Long> {
}
