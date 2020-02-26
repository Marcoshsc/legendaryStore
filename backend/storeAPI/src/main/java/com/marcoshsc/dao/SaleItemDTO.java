package com.marcoshsc.dao;

public class SaleItemDTO {
	
	private Long productId;
	
	private Long quantity;

	public SaleItemDTO(Long productId, Long quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductid(Long productId) {
		this.productId = productId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}
