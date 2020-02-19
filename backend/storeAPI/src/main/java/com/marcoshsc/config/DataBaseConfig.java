package com.marcoshsc.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfig {
	
	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSource = DataSourceBuilder.create();
		dataSource.url("jdbc:postgresql://localhost:5432/storeAPI");
		dataSource.username("postgres");
		dataSource.password("teste");
		return dataSource.build();
	}
	
}
