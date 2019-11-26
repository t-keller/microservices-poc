package com.example.usergdprcqrs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usergdprcqrs.entities.Consent;
import com.example.usergdprcqrs.repositories.ConsentRepository;

@RestController
public class ConsentController {

	@Autowired
	private ConsentRepository consentRepository;

	@GetMapping(path = "consents")
	public List<Consent> getConsents() {
		return consentRepository.findAll();
	}
}
