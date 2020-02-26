package com.marcoshsc.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.marcoshsc.exceptions.InvalidSale;
import com.marcoshsc.exceptions.InvalidStock;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.interfaces.Validated;

@Entity(name = "sales")
public class Sale implements Validated {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Column(name = "sale_date")
	private LocalDate date;
	
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<SaleItem> saleItems = new ArrayList<SaleItem>();
	
	public Sale() {
		super();
	}

	public Sale(Client client) throws NullField {
		super();
		this.client = client;
		this.date = LocalDate.now();
		checkIrregularities();
	}

	@Override
	public void checkIrregularities() throws NullField {
		if(client == null) throw new NullField("sale(client)");
		if(date == null) throw new NullField("sale(date)");
	}
	
	public static Sale makeSale(Client client, List<SaleItem> saleItems) throws InvalidSale, NullField {
		InvalidSale exc = new InvalidSale();
		Sale sale = new Sale(client);
		for(SaleItem si: saleItems) {
			si.setFinalPrice(si.getProduct().getPrice() * si.getQuantity());
			Product saleItemProduct = si.getProduct();
			if(saleItemProduct.getStock() < si.getQuantity())
				exc.getProducts().add(saleItemProduct);
			si.setSale(sale);
		}
		if(exc.getProducts().size() != 0)
			throw exc;
		
		sale.setSaleItems(saleItems);
		return sale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<SaleItem> getSaleItems() {
		return saleItems;
	}

	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}
	
}
