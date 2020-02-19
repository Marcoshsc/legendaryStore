package com.marcoshsc.storeAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.marcoshsc.domain.Product;
import com.marcoshsc.services.ProductService;

@SpringBootApplication
@EntityScan(basePackages = {"com.marcoshsc.domain"})
@ComponentScan(basePackages = {"com.marcoshsc.config", "com.marcoshsc.services"})
@EnableJpaRepositories(basePackages = {"com.marcoshsc.repos"})
public class StoreApiApplication implements CommandLineRunner {

	@Autowired
	private ProductService productService;
	
	public static void main(String[] args) {
		SpringApplication.run(StoreApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productService.add(new Product("Balinha", 300l, 12.4d));
	}

}
