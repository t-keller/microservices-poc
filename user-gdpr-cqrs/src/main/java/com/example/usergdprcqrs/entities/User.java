package com.example.usergdprcqrs.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "users")
@Data
public class User {

	@Id
	private int id;
	private String firstname;
	private String lastname;
}
