package com.example.gdprservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gdprservice.entities.Consent;
import com.example.gdprservice.repositories.ConsentRepository;

@RestController
public class ConsentController {

	@Autowired
	private ConsentRepository consentRepository;

	@GetMapping(path = "consents")
	public List<Consent> getConsents() {
		return consentRepository.findAll();
	}

	@GetMapping(path = "consents/{id}")
	public Consent getConsent(@PathVariable("id") final int id) {
		return consentRepository.findById(id).orElseThrow();
	}

	@PostMapping(path = "consents")
	public ResponseEntity<Consent> createConsent(@RequestBody final Consent consent) {
		Consent consentCreated = consentRepository.save(consent);
		return new ResponseEntity<Consent>(consentCreated, HttpStatus.CREATED);
	}
}
