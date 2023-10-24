package com.lawencon.mailservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.lawencon.mailservice.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
	
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent: %s", message));
        kafkaTemplate.send(topicName, message);
	}

}
