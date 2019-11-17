package com.example.userservice.dto;

import com.example.userservice.entities.User;

public class UserEvent {

	public enum UserEventType {
		CREATE, DELETE;
	}

	private UserEventType type;
	private User user;

	public static UserEvent of(UserEventType type, User user) {
		return new UserEvent(type, user);
	}

	private UserEvent(UserEventType type, User user) {
		this.type = type;
		this.user = user;
	}

	public UserEventType getType() {
		return type;
	}

	public void setType(UserEventType type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
