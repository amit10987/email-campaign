package com.ub.email.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ub")
public class EmailCampaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailCampaignApplication.class, args);
	}

}
