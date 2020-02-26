package com.marcoshsc.exceptions;

public class VinculatedClass extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int totalAssociated;
	
	public VinculatedClass(int totalAssociated) {
		super();
		this.totalAssociated = totalAssociated;
	}

	@Override
	public String getMessage() {
		return "This element has " + Integer.toString(totalAssociated) + " associations in the database. Can't remove it.";
	}
	
}
