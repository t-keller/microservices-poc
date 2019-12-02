package com.example.gdprservice.dto;

import com.example.gdprservice.entities.Consent;

import lombok.Data;
import lombok.NonNull;

@Data(staticConstructor = "of")
public class ConsentEvent {

	public enum ConsentEventType {
		CREATE
	}

	@NonNull
	private ConsentEventType type;
	@NonNull
	private Consent consent;
}
