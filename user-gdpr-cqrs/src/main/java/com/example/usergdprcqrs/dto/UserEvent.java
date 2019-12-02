package com.example.usergdprcqrs.dto;

import com.example.usergdprcqrs.entities.User;

import lombok.Data;
import lombok.NonNull;

@Data(staticConstructor = "of")
public class UserEvent {

	public enum UserEventType {
		CREATE, DELETE;
	}

	@NonNull
	private UserEventType type;

	@NonNull
	private User user;
}
