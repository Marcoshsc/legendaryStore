package com.marcoshsc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.marcoshsc.exceptions.InvalidName;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.interfaces.Validated;

@Entity(name = "clients")
public class Client implements Validated {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	public Client() {
		super();
	}

	public Client(String name) throws InvalidName, NullField {
		super();
		this.name = name;
		checkIrregularities();
	}

	@Override
	public void checkIrregularities() throws InvalidName, NullField {
		if(name == null) throw new NullField("client(name)");
		if(name.isEmpty()) throw new InvalidName();
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

	
	
}
