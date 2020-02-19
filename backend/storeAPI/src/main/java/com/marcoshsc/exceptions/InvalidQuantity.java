package com.marcoshsc.exceptions;

public class InvalidQuantity extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long quantity;

	public InvalidQuantity(Long quantity) {
		super();
		this.quantity = quantity;
	}

	@Override
	public String getMessage() {
		return "Invalid quantity detected: " + quantity.toString() + ".";
	}
	
}
