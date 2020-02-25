package com.marcoshsc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcoshsc.domain.SaleItem;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
	
}
