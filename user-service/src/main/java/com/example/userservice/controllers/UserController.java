package com.example.userservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.dto.UserEvent;
import com.example.userservice.dto.UserEvent.UserEventType;
import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;

/**
 * UserController overrides the basic CRUD provided by UserRepository (exposed
 * via RepositoryRestResource)
 */
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;

	@PostMapping(path = "users")
	public ResponseEntity<User> createUser(@RequestBody final User user) {
		User userCreated = userRepository.save(user);

		UserEvent userEvent = UserEvent.of(UserEventType.CREATE, userCreated);

		Message<UserEvent> message = MessageBuilder.withPayload(userEvent).setHeader(KafkaHeaders.TOPIC, "test")
				.build();

		kafkaTemplate.send(message);

		return new ResponseEntity<User>(userCreated, HttpStatus.CREATED);
	}

	@GetMapping(path = "users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "users/{id}")
	public User getUser(@PathVariable("id") final int id) {
		return userRepository.findById(id).orElseThrow();
	}

	@DeleteMapping(path = "users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") final int id) {
		userRepository.deleteById(id);

		User user = new User();
		user.setId(id);

		UserEvent userEvent = UserEvent.of(UserEventType.DELETE, user);

		Message<UserEvent> message = MessageBuilder.withPayload(userEvent).setHeader(KafkaHeaders.TOPIC, "test")
				.build();

		kafkaTemplate.send(message);

		return ResponseEntity.noContent().build();
	}
}
