package com.lawencon.mailservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lawencon.mailservice.dto.User;
import com.lawencon.mailservice.service.JsonKafkaConsumerService;


@Service
public class JsonKafkaConsumerImpl implements JsonKafkaConsumerService {
	   private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumerImpl.class);

	    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
	    public void consume(User user){
	        LOGGER.info(String.format("Json message recieved -> %s", user.toString()));
	    }
}
