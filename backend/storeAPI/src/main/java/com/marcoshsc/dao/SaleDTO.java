package com.marcoshsc.dao;

import java.util.List;

public class SaleDTO {
	
	private Long clientId;
	
	private List<SaleItemDTO> saleItems;

	public SaleDTO(Long clientId, List<SaleItemDTO> saleItems) {
		super();
		this.clientId = clientId;
		this.saleItems = saleItems;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public List<SaleItemDTO> getSaleItems() {
		return saleItems;
	}

	public void setSaleItems(List<SaleItemDTO> saleItems) {
		this.saleItems = saleItems;
	}
	
}
