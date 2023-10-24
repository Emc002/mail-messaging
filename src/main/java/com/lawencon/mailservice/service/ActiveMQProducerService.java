package com.lawencon.mailservice.service;

import com.lawencon.mailservice.dto.User;

public interface ActiveMQProducerService {
	public void sendActiveMqMessage(User user);
}
