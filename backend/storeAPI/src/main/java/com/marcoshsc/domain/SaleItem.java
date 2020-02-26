package com.marcoshsc.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcoshsc.exceptions.InvalidQuantity;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.interfaces.Validated;

@Entity(name = "sale_items")
public class SaleItem implements Validated {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "sale_id")
	@JsonIgnore
	private Sale sale;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "product_id")
	private Product product;

	private Long quantity;

	@Column(name = "final_price")
	private Double finalPrice;

	public SaleItem() {
		super();
	}

	public SaleItem(Product product, Long quantity) throws NullField, InvalidQuantity {
		super();
		this.product = product;
		this.quantity = quantity;
		checkIrregularities();
		this.finalPrice = quantity * product.getPrice();
	}
	
	@Override
	public void checkIrregularities() throws NullField, InvalidQuantity {
		if(product == null) throw new NullField("saleItem(product)");
		if(quantity == null) throw new NullField("saleItem(quantity)");
		if(quantity < 0) throw new InvalidQuantity(quantity);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
}
