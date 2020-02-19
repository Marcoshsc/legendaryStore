package com.marcoshsc.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marcoshsc.domain.Client;
import com.marcoshsc.exceptions.InvalidName;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.repos.ClientRepository;

@Repository
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepo;
	
	public Client add(Client client) throws InvalidName, NullField {
		client.checkIrregularities();
		return clientRepo.save(client);
	}
	
	public Client remove(Long id) throws NoSuchElementException {
		Client entity = clientRepo.findById(id).get();
		clientRepo.delete(entity);
		return entity;
	}
	
	public Client update(Long id, Client client) throws InvalidName, NoSuchElementException, NullField {
		client.checkIrregularities();
		Client entity = clientRepo.findById(id).get();
		entity.setName(client.getName());
		return clientRepo.save(entity);
	}
	
}
