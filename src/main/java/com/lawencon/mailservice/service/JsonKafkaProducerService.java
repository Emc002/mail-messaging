package com.lawencon.mailservice.service;

import com.lawencon.mailservice.dto.User;

public interface JsonKafkaProducerService {
	public void sendMessage(User data);
}
