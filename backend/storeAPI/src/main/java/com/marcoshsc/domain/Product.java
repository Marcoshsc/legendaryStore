package com.marcoshsc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.marcoshsc.exceptions.InvalidName;
import com.marcoshsc.exceptions.InvalidPrice;
import com.marcoshsc.exceptions.InvalidStock;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.interfaces.Validated;

@Entity(name = "products")
public class Product implements Validated {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
