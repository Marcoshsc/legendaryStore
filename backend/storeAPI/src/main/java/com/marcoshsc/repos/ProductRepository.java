package com.marcoshsc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcoshsc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
