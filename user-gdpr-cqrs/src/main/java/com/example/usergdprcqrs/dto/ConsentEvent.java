package com.example.usergdprcqrs.dto;

public class ConsentEvent {

	public enum ConsentEventType {
		CREATE
	}

	private ConsentEventType type;
	private ConsentDto consent;

	public static ConsentEvent of(ConsentEventType type, ConsentDto consent) {
		return new ConsentEvent(type, consent);
	}

	private ConsentEvent(ConsentEventType type, ConsentDto consent) {
		this.setType(type);
		this.setConsent(consent);
	}

	public ConsentEventType getType() {
		return type;
	}

	public void setType(ConsentEventType type) {
		this.type = type;
	}

	public ConsentDto getConsent() {
		return consent;
	}

	public void setConsent(ConsentDto consent) {
		this.consent = consent;
	}
}
