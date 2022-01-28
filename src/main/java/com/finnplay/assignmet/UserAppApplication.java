package com.finnplay.assignmet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UserAppApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAppApplication.class);

	public static void main(String[] args) {
		
		LOGGER.info(" Application - Finnplay assignment started...");
		
		SpringApplication.run(UserAppApplication.class, args);
	}
}
