package com.jobtracker.jobtrackerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.jobtracker.models")
@SpringBootApplication
public class JobtrackerappApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(JobtrackerappApplication.class, args);
	}

}
