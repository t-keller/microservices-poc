package com.example.usergdprservice.dto;

import lombok.Data;

@Data
public class Consent {
	private int id;
	private String personId;
	private boolean optin;

	// Coming from GET /users
	private String firstname;
	private String lastname;
}
