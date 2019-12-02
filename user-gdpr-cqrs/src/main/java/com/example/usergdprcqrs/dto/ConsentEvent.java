package com.example.usergdprcqrs.dto;

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
	private ConsentDto consent;
}
