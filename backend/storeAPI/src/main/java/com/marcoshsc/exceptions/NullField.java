package com.marcoshsc.exceptions;

public class NullField extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fieldName;

	public NullField(String fieldName) {
		super();
		this.fieldName = fieldName;
	}

	@Override
	public String getMessage() {
		return "Null field detected: " + fieldName;
	}

}
