package com.marcoshsc.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcoshsc.dao.ErrorDAO;
import com.marcoshsc.dao.FinalResponse;
import com.marcoshsc.domain.Client;
import com.marcoshsc.exceptions.InvalidName;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public FinalResponse<? extends Object> getAllClients() {
		List<Client> dbClients = clientService.getAll();
		return new FinalResponse<Client>("200", dbClients);
	}
	
	@GetMapping("/{id}")
	public FinalResponse<? extends Object> getClientById(@PathVariable("id") Long id) {
		try {
			Client dbClient = clientService.find(id);
			return new FinalResponse<Client>("200", dbClient);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID, client not found");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@PostMapping
	public FinalResponse<? extends Object> addClient(@RequestBody Client c) {
		try {
			Client dbClient = clientService.add(c);
			return new FinalResponse<Object>("201", dbClient);
		} catch(InvalidName | NullField exc) {
			ErrorDAO error = new ErrorDAO(exc.getMessage());
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@PutMapping("/{id}")
	public FinalResponse<? extends Object> updateClient(@PathVariable("id") Long id, @RequestBody Client c) {
		try {
			Client dbClient = clientService.update(id, c);
			return new FinalResponse<Client>("200", dbClient);
		} catch (InvalidName | NullField exc) {
			ErrorDAO error = new ErrorDAO(exc.getMessage());
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID, client not found");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@DeleteMapping("/{id}")
	public FinalResponse<? extends Object> deleteClient(@PathVariable("id") Long id) {
		try {
			Client dbClient = clientService.remove(id);
			return new FinalResponse<Object>("200", dbClient);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID, client not found");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
}
