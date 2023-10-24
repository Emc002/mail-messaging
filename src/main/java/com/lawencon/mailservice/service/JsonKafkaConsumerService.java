package com.lawencon.mailservice.service;

import com.lawencon.mailservice.dto.User;

public interface JsonKafkaConsumerService {
	public void consume(User user);
}
