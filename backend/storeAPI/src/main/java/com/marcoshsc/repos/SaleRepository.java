package com.marcoshsc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcoshsc.domain.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
	
}
