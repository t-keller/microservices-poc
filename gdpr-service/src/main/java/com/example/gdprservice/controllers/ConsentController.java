package com.example.gdprservice.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gdprservice.dto.ConsentEvent;
import com.example.gdprservice.dto.ConsentEvent.ConsentEventType;
import com.example.gdprservice.entities.Consent;
import com.example.gdprservice.repositories.ConsentRepository;

@RestController
public class ConsentController {

	Logger logger = LoggerFactory.getLogger(ConsentController.class);

	@Autowired
	private ConsentRepository consentRepository;

	@Autowired
	KafkaTemplate<String, ConsentEvent> kafkaTemplate;

	@Value("${app.topics.consent}")
	private String consentTopicName;

	@GetMapping(path = "consents")
	public List<Consent> getConsents() {
		return consentRepository.findAll();
	}

	@GetMapping(path = "consents/{id}")
	public Consent getConsent(@PathVariable("id") final int id) {
		return consentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("consent not found"));
	}

	@PostMapping(path = "consents")
	public ResponseEntity<Consent> createConsent(@RequestBody final Consent consent) {
		Consent consentCreated = consentRepository.save(consent);

		ConsentEvent consentEvent = ConsentEvent.of(ConsentEventType.CREATE, consentCreated);

		Message<ConsentEvent> message = MessageBuilder.withPayload(consentEvent)
				.setHeader(KafkaHeaders.TOPIC, consentTopicName).build();

		logger.info("Broadcasting event: {}", message);

		kafkaTemplate.send(message);
		return new ResponseEntity<Consent>(consentCreated, HttpStatus.CREATED);
	}
}
