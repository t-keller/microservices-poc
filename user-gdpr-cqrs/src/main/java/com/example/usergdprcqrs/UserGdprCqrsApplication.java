package com.example.usergdprcqrs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UserGdprCqrsApplication {

	@Value("${app.topics.user}")
	private String userTopicName;

	@Value("${app.topics.consent}")
	private String consentTopicName;

	public static void main(String[] args) {
		SpringApplication.run(UserGdprCqrsApplication.class, args);
	}

	@Bean
	public NewTopic userTopic() {
		return new NewTopic(userTopicName, 1, (short) 1);
	}

	@Bean
	public NewTopic consentTopic() {
		return new NewTopic(consentTopicName, 1, (short) 1);
	}

}
