package com.example.usergdprcqrs.controllers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.usergdprcqrs.entities.User;

@Component
public class MyKafkaListener {

	@KafkaListener(topics = "test")
	public void processMessage(@Payload User user) {
		System.out.println("My username: " + user.getUsername());
	}
}
