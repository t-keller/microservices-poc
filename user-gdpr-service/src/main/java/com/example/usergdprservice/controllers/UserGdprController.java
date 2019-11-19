package com.example.usergdprservice.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.usergdprservice.dto.Consent;
import com.example.usergdprservice.dto.User;

@RestController
public class UserGdprController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = { "/consents-full" })
	public Consent[] getFullConsents() {
		User[] users = restTemplate.getForObject("http://127.0.0.1:8080/users", User[].class);
		Consent[] consents = restTemplate.getForObject("http://127.0.0.1:8081/consents", Consent[].class);

		List<User> userList = new ArrayList<>(Arrays.asList(users));
		List<Consent> consentList = new ArrayList<>(Arrays.asList(consents));

		for (Consent consent : consentList) {

			User relatedUser = userList.stream().filter(user -> user.getId().equals(consent.getPersonId())).findAny()
					.orElse(null);

			consent.setFirstname(relatedUser.getFirstname());
			consent.setLastname(relatedUser.getLastname());
		}

		return consents;
	}
}
