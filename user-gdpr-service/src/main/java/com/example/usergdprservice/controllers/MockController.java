package com.example.usergdprservice.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/mocks"})
public class MockController {

	@GetMapping(value = {"/users"})
	public List<Map<String, Object>> getUsers() {
		Map<String, Object> user = new HashMap<>();
		user.put("id", "1");
		user.put("username", "Thomas");
		return Arrays.asList(user);
	}

	@GetMapping(value = {"/consents"})
	public List<Map<String, Object>> getConsents() {
		Map<String, Object> treatment = new HashMap<>();
		treatment.put("id", 1);
		treatment.put("name", "Newsletter");

		Map<String, Object> consent = new HashMap<>();
		consent.put("id", 1);
		consent.put("personId", "1");
		consent.put("treatment", treatment);
		consent.put("optin", true);

		return Arrays.asList(consent);
	}
}
