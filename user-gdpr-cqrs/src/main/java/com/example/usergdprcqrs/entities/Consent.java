package com.example.usergdprcqrs.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity(name = "consents")
@Data
public class Consent {

	@Id
	private int id;

	@ManyToOne
	private User person;

	@ManyToOne
	private Treatment treatment;

	private boolean optin;
}
