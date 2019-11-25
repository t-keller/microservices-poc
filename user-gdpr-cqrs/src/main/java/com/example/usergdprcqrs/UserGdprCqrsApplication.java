package com.example.usergdprcqrs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserGdprCqrsApplication {

	@Value("${app.topics.user}")
	private String userTopicName;

	public static void main(String[] args) {
		SpringApplication.run(UserGdprCqrsApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return new NewTopic(userTopicName, 1, (short) 1);
	}
}
