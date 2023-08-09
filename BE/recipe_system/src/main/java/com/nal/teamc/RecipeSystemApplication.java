package com.nal.teamc;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeSystemApplication {

	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(RecipeSystemApplication.class);
//		app.setDefaultProperties(Collections.singletonMap("server.port", "8084"));
//		app.run(args);
		SpringApplication.run(RecipeSystemApplication.class, args);
	}

}
