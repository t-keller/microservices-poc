package com.example.userservice.dto;

import com.example.userservice.entities.User;

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
