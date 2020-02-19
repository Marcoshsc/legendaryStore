package com.marcoshsc.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy = "sale")
	private List<SaleItem> saleItems;
	
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
	
}
