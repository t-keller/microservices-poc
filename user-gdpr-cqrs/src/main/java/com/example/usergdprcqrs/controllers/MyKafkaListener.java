package com.example.usergdprcqrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.usergdprcqrs.dto.ConsentEvent;
import com.example.usergdprcqrs.dto.ConsentEvent.ConsentEventType;
import com.example.usergdprcqrs.dto.UserEvent;
import com.example.usergdprcqrs.dto.UserEvent.UserEventType;
import com.example.usergdprcqrs.repositories.ConsentRepository;
import com.example.usergdprcqrs.repositories.UserRepository;
import com.google.gson.Gson;

@Component
public class MyKafkaListener {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConsentRepository consentRepository;

	@KafkaListener(topics = "${app.topics.user}")
	public void processUserMessage(@Payload String payload) {
		Gson gson = new Gson();
		UserEvent userEvent = gson.fromJson(payload, UserEvent.class);

		if (userEvent.getType() == UserEventType.CREATE) {
			System.out.println("Saving user: " + userEvent.getUser().getId());
			userRepository.save(userEvent.getUser());
		} else if (userEvent.getType() == UserEventType.DELETE) {
			System.out.println("Deleting user: " + userEvent.getUser().getId());
			userRepository.deleteById(userEvent.getUser().getId());
		}
	}

	@KafkaListener(topics = "${app.topics.consent}")
	public void processConsentMessage(@Payload String payload) {
		Gson gson = new Gson();
		ConsentEvent consentEvent = gson.fromJson(payload, ConsentEvent.class);

		if (consentEvent.getType() == ConsentEventType.CREATE) {
			System.out.println("Saving consent: " + consentEvent.getConsent().getId());
			consentRepository.save(consentEvent.getConsent());
		}
	}
}
