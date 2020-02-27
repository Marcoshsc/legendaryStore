package com.marcoshsc.dao;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.marcoshsc.views.ProductView;

@JsonView(ProductView.SaleItemView.class)
public class FinalResponse<T extends Object> {
	
	private String status;
	
	private List<T> data;

	public FinalResponse(String status, List<T> data) {
		super();
		this.status = status;
		this.data = data;
	}
	
	public FinalResponse(String status, T data) {
		super();
		this.status = status;
		this.data = new ArrayList<T>();
		this.data.add(data);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
}
