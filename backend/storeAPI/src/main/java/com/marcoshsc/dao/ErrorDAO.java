package com.marcoshsc.dao;

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
