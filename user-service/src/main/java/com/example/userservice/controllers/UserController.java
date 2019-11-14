package com.example.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;

/**
 * UserController overrides the basic CRUD provided by UserRepository (exposed
 * via RepositoryRestResource)
 */
@RepositoryRestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;
	
	@RequestMapping(path = "users", method = RequestMethod.POST, produces = "application/hal+json")
	public ResponseEntity<User> createUser(@RequestBody final User user, PersistentEntityResourceAssembler assembler) {
		User userCreated = userRepository.save(user);

		Message<User> message = MessageBuilder
                .withPayload(userCreated)
                .setHeader(KafkaHeaders.TOPIC, "test")
                .build();
		
		kafkaTemplate.send(message);
		
		// TODO: Return a HAL response
		return new ResponseEntity<User>(userCreated, HttpStatus.CREATED);
	}
}
