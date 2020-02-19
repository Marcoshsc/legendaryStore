package com.marcoshsc.exceptions;

public class InvalidStock extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private Long stock;

	public InvalidStock(Long stock) {
		super();
		this.stock = stock;
	}
	
	@Override
	public String getMessage() {
		return String.format("Invalid Stock detected: %s.", stock.toString());
	}
	
}
