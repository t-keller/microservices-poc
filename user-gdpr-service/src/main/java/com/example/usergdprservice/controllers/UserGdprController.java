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

	@GetMapping(value = {"/consents-full"})
	public Consent[] getFullConsents() {
		User[] users = restTemplate.getForObject("http://127.0.0.1:8080/mocks/users", User[].class);
		Consent[] consents = restTemplate.getForObject("http://127.0.0.1:8080/mocks/consents", Consent[].class);

		//		// Opt1. Standard double Java for
		//		for (Consent consent : consents) {
		//			String username = null;
		//
		//			for (User user : users) {
		//				if (user.getId().equals(consent.getPersonId())) {
		//					username = user.getUsername();
		//					break;
		//				}
		//			}
		//
		//			consent.setUsername(username);
		//		}

		//		// Opt2. Standard Java for and stream to find the username
		//		for (Consent consent : consents) {
		//			List<User> userList = new ArrayList(Arrays.asList(users));
		//			
		//			String username = userList.stream()
		//				.filter(user -> user.getId().equals(consent.getPersonId()))
		//				.findAny()
		//				.map(User::getUsername)
		//				.orElse(null);
		//			
		//			consent.setUsername(username);
		//		}

		// Opt3. Full Java8 streams
		List<User> userList = new ArrayList<>(Arrays.asList(users));
		List<Consent> consentList = new ArrayList<>(Arrays.asList(consents));

		consentList.stream()
			.forEach(consent ->
				consent.setUsername(userList.stream()
						.filter(user -> user.getId().equals(consent.getPersonId()))
						.findAny()
						.map(User::getUsername)
						.orElse(null)
						));

		return consents;
	}
}
