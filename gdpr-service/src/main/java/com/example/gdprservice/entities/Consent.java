package com.example.gdprservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity(name = "consents")
@Data
public class Consent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String personId;

	@ManyToOne
	private Treatment treatment;

	private boolean optin;
}
