package com.example.usergdprcqrs.configurations;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.usergdprcqrs.entities.User;

@Configuration
@EnableKafka
public class ReceiverConfig {

	@Autowired
	private ConsumerFactory<String, User> consumerFactory;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
		
		ConsumerFactory<String, User> myConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerFactory.getConfigurationProperties(), new StringDeserializer(), new JsonDeserializer<>(User.class)); 
		factory.setConsumerFactory(myConsumerFactory);

		return factory;
	}
}
