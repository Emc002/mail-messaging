package com.lawencon.mailservice.service;

import com.lawencon.mailservice.dto.User;

public interface ActiveMQConsumerService {
	public void messageListener(User user);
}
