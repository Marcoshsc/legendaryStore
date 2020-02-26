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
import com.marcoshsc.domain.Product;
import com.marcoshsc.exceptions.InvalidName;
import com.marcoshsc.exceptions.InvalidPrice;
import com.marcoshsc.exceptions.InvalidStock;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.exceptions.VinculatedClass;
import com.marcoshsc.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public FinalResponse<? extends Object> getAllProducts() {
		List<Product> dbProducts = productService.getAllProducts();
		return new FinalResponse<Product>("200", dbProducts);
	}
	
	@GetMapping("/{id}")
	public FinalResponse<? extends Object> getProductById(@PathVariable("id") Long id) {
		try {
			Product p = productService.find(id);
			return new FinalResponse<Product>("200", p);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID, product not found.");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@PostMapping
	public FinalResponse<? extends Object> addProduct(@RequestBody Product p) {
		try {
			Product dbProduct = productService.add(p);
			return new FinalResponse<Product>("201", dbProduct);
		} catch(InvalidStock | NullField | InvalidPrice | InvalidName  exc) {
			ErrorDAO error = new ErrorDAO(exc.getMessage());
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@PutMapping("/{id}")
	public FinalResponse<? extends Object> updateProduct(@PathVariable("id") Long id, @RequestBody Product p) {
		try {
			Product dbProduct = productService.update(id, p);
			return new FinalResponse<Product>("200", dbProduct);
		} catch(InvalidStock | InvalidName | InvalidPrice | NullField exc) {
			ErrorDAO error = new ErrorDAO(exc.getMessage());
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID, product not found.");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@DeleteMapping("/{id}")
	public FinalResponse<? extends Object> deleteProduct(@PathVariable("id") Long id) {
		try {
			Product p = productService.remove(id);
			return new FinalResponse<Product>("200", p);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID, product not found.");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(VinculatedClass exc) {
			ErrorDAO error = new ErrorDAO(exc.getMessage());
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
}
