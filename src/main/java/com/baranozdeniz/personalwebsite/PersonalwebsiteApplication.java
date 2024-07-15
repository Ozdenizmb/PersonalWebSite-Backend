package com.baranozdeniz.personalwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PersonalwebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalwebsiteApplication.class, args);
	}

}
