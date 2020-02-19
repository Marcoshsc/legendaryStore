package com.marcoshsc.exceptions;

public class InvalidName extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "No name was introduced.";
	}
	
}
