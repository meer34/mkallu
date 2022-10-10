package com.hunter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HunterApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HunterApplication.class, args);
	}

}
