package com.example.medcardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories
@EnableScheduling
public class MedcardserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedcardserviceApplication.class, args);

		System.out.println("Hello my docker App!");
	}

}
