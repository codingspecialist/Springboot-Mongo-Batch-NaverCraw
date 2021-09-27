package com.cos.newssave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NewsSaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsSaveApplication.class, args);
	}

}
