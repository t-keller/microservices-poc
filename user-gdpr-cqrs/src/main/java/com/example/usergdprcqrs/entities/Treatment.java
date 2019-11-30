package com.example.usergdprcqrs.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "treatments")
public class Treatment {

	@Id
	private int id;
	private String name;

	public Treatment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
