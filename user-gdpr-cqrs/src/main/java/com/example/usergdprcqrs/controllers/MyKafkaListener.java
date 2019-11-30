package com.example.usergdprcqrs.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.usergdprcqrs.dto.ConsentDto;
import com.example.usergdprcqrs.dto.ConsentEvent;
import com.example.usergdprcqrs.dto.ConsentEvent.ConsentEventType;
import com.example.usergdprcqrs.dto.UserEvent;
import com.example.usergdprcqrs.dto.UserEvent.UserEventType;
import com.example.usergdprcqrs.entities.Consent;
import com.example.usergdprcqrs.entities.User;
import com.example.usergdprcqrs.repositories.ConsentRepository;
import com.example.usergdprcqrs.repositories.UserRepository;
import com.google.gson.Gson;

@Component
public class MyKafkaListener {

	Logger logger = LoggerFactory.getLogger(MyKafkaListener.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConsentRepository consentRepository;

	@KafkaListener(topics = "${app.topics.user}")
	public void processUserMessage(@Payload String payload) {
		logger.info("Receiving event: {}", payload);

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
		logger.info("Receiving event: {}", payload);

		Gson gson = new Gson();
		ConsentEvent consentEvent = gson.fromJson(payload, ConsentEvent.class);

		if (consentEvent.getType() == ConsentEventType.CREATE) {
			System.out.println("Saving consent: " + consentEvent.getConsent().getId());
			consentRepository.save(convertConsentDtoToEntity(consentEvent.getConsent()));
		}
	}

	private Consent convertConsentDtoToEntity(ConsentDto consentDto) {
		Consent consent = new Consent();
		consent.setId(consentDto.getId());
		consent.setOptin(consentDto.isOptin());
		consent.setTreatment(consentDto.getTreatment());

		Optional<User> person = userRepository.findById(Integer.valueOf(consentDto.getPersonId()));
		logger.info("Looking for the user: {}", consentDto.getPersonId());

		consent.setPerson(person.get());

		return consent;
	}
}
