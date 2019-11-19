package com.example.usergdprservice.dto;

public class Consent {
	private int id;
	private String personId;
	private boolean optin;

	// Coming from GET /users
	private String firstname;
	private String lastname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public boolean isOptin() {
		return optin;
	}

	public void setOptin(boolean optin) {
		this.optin = optin;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
