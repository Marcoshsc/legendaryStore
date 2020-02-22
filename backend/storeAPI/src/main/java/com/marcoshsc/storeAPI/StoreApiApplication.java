package com.marcoshsc.storeAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.marcoshsc.domain"})
@ComponentScan(basePackages = {"com.marcoshsc.config", "com.marcoshsc.services", "com.marcoshsc.controllers"})
@EnableJpaRepositories(basePackages = {"com.marcoshsc.repos"})
public class StoreApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StoreApiApplication.class, args);
	}

}
