package com.context;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class ApiRestHeladeriaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiRestHeladeriaApplication.class, args);
	}
	
}


