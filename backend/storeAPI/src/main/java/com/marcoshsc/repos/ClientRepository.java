package com.marcoshsc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcoshsc.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
