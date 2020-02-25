package com.marcoshsc.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoshsc.domain.Product;
import com.marcoshsc.exceptions.InvalidName;
import com.marcoshsc.exceptions.InvalidPrice;
import com.marcoshsc.exceptions.InvalidStock;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.exceptions.VinculatedClass;
import com.marcoshsc.repos.ProductRepository;
import com.marcoshsc.repos.SaleItemRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SaleItemRepository saleItemRepo;
	
	public Product add(Product product) throws InvalidStock, InvalidName, InvalidPrice, NullField {
		product.checkIrregularities();
		return productRepo.save(product);
	}
	
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	public Product remove(Long id) throws NoSuchElementException {
		Product toBeFound = productRepo.findById(id).get();
		productRepo.delete(toBeFound);
		return toBeFound;
	}
	
	public Product update(Long id, Product product) throws NoSuchElementException, InvalidStock, InvalidName, InvalidPrice, 
	NullField {
		Product toBeUpdated = productRepo.findById(id).get();
		product.checkIrregularities();
		toBeUpdated.setName(product.getName());
		toBeUpdated.setPrice(product.getPrice());
		toBeUpdated.setStock(product.getStock());
		return productRepo.save(toBeUpdated);
	}
	
	public void addStock(Long id, Long stock) throws InvalidStock, NoSuchElementException, VinculatedClass {
		Product entity = productRepo.findById(id).get();
		entity.addStock(stock);
	}
	
	public Product find(Long id) throws NoSuchElementException {
		return productRepo.findById(id).get();
	}
	
}
