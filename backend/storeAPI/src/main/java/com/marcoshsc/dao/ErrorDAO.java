package com.marcoshsc.dao;

import com.fasterxml.jackson.annotation.JsonView;
import com.marcoshsc.views.ProductView;

@JsonView(ProductView.SaleItemView.class)
public class ErrorDAO {
	
	private final String message = "An error has occurred";
	
	private String errorLog;

	public ErrorDAO(String errorLog) {
		super();
		this.errorLog = errorLog;
	}

	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}

	public String getMessage() {
		return message;
	}
	
}
