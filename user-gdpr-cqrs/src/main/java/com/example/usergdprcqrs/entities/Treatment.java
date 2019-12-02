package com.example.usergdprcqrs.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "treatments")
@Data
public class Treatment {

	@Id
	private int id;
	private String name;
}
