package com.marcoshsc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;
import com.marcoshsc.exceptions.InvalidName;
import com.marcoshsc.exceptions.InvalidPrice;
import com.marcoshsc.exceptions.InvalidStock;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.interfaces.Validated;
import com.marcoshsc.views.ProductView;

@Entity(name = "products")
public class Product implements Validated {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(ProductView.SaleItemView.class)
	private Long id;
	
	private String name;
	
	private Long stock;
	
	private Double price;
	
	public Product() {
		super();
	}

	public Product(String name, Long stock, Double price) throws InvalidPrice, InvalidStock, InvalidName, NullField {
		super();
		this.name = name;
		this.stock = stock;
		this.price = price;
		checkIrregularities();
	}
	
	public void checkIrregularities() throws InvalidPrice, InvalidStock, InvalidName, NullField {
		if(name == null) throw new NullField("product(name)");
		if(stock == null) throw new NullField("product(stock)");
		if(price == null) throw new NullField("product(price)");
		if(stock < 0) throw new InvalidStock(stock);
		if(price < 0) throw new InvalidPrice(price);
		if(name.isEmpty()) throw new InvalidName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void addStock(Long quantity) throws InvalidStock {
		if(quantity < 0) throw new InvalidStock(quantity);
		this.stock += quantity;
	}
	
	public void removeStock(Long quantity) throws InvalidStock {
		if(this.stock < quantity) throw new InvalidStock(quantity);
		this.stock -= quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
