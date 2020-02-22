package com.marcoshsc.exceptions;

import java.util.HashSet;
import java.util.Set;

import com.marcoshsc.domain.Product;

public class InvalidSale extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<Product> products = new HashSet<Product>();
	
	public String getMessage() {
		StringBuilder string = new StringBuilder();
		string.append("Invalid sale detected: Not enough stock for the following products: ");
		for(Product p: products) {
			string.append(p.getName() + ", ");
		}
		return string.toString();
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
