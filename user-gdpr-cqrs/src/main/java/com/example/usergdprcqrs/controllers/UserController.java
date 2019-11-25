package com.example.usergdprcqrs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usergdprcqrs.entities.User;
import com.example.usergdprcqrs.repositories.UserRepository;

/**
 * UserController overrides the basic CRUD provided by UserRepository (exposed
 * via RepositoryRestResource)
 */
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
