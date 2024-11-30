package com.example.projectrbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class ProjectrbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectrbacApplication.class, args);
		System.out.println("Server is Started!");

	}

}

