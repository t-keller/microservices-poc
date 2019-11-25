package com.example.usergdprcqrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.usergdprcqrs.dto.UserEvent;
import com.example.usergdprcqrs.dto.UserEvent.UserEventType;
import com.example.usergdprcqrs.repositories.UserRepository;
import com.google.gson.Gson;

@Component
public class MyKafkaListener {

	@Autowired
	UserRepository userRepository;

	@KafkaListener(topics = "test")
	public void processMessage(@Payload String payload) {
		Gson gson = new Gson();
		UserEvent userEvent = gson.fromJson(payload, UserEvent.class);

		if (userEvent.getType() == UserEventType.CREATE) {
			System.out.println("Saving user: " + userEvent.getUser().getId());
			userRepository.save(userEvent.getUser());
		}
	}
}
