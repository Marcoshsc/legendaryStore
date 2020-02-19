package com.marcoshsc.exceptions;

public class InvalidPrice extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private Double price;
	
	public InvalidPrice(Double price) {
		super();
		this.price = price;
	}

	@Override
	public String getMessage() {
		return "Invalid quantity detected: " + price.toString() + ".";
	}
	
}
