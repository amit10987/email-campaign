package com.ub.email.boot;

import com.ub.email.entity.Campaign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.ub")
@EnableJpaRepositories(basePackages = { "com.ub.email.repository" })
@EntityScan(basePackageClasses = Campaign.class)
public class EmailCampaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailCampaignApplication.class, args);
	}

}
