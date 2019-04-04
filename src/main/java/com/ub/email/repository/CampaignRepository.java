package com.ub.email.repository;

import com.ub.email.entity.Campaign;
import org.springframework.data.repository.CrudRepository;

public interface CampaignRepository extends CrudRepository<Campaign, Long> {
}
