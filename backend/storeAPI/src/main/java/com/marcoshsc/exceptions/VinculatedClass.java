package com.marcoshsc.exceptions;

public class VinculatedClass extends Exception {

	@Override
	public String getMessage() {
		return "This element is associated with another in the database. Can not remove it.";
	}
	
}
