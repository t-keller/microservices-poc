package com.example.gdprservice.dto;

import com.example.gdprservice.entities.Consent;

public class ConsentEvent {

	public enum ConsentEventType {
		CREATE
	}

	private ConsentEventType type;
	private Consent consent;

	public static ConsentEvent of(ConsentEventType type, Consent consent) {
		return new ConsentEvent(type, consent);
	}

	private ConsentEvent(ConsentEventType type, Consent consent) {
		this.setType(type);
		this.setConsent(consent);
	}

	public ConsentEventType getType() {
		return type;
	}

	public void setType(ConsentEventType type) {
		this.type = type;
	}

	public Consent getConsent() {
		return consent;
	}

	public void setConsent(Consent consent) {
		this.consent = consent;
	}
}
