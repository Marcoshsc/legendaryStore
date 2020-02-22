package com.marcoshsc.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marcoshsc.domain.Client;
import com.marcoshsc.domain.Product;
import com.marcoshsc.exceptions.InvalidName;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.repos.ClientRepository;
import com.marcoshsc.repos.SaleRepository;

@Repository
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private SaleRepository salesRepo;
	
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
	
	public Client find(Long id) throws NoSuchElementException {
		return clientRepo.findById(id).get();
	}
	
	public List<Client> getAll() {
		return clientRepo.findAll();
	}
	
}
