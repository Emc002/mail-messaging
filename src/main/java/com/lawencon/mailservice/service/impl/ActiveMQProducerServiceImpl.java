package com.lawencon.mailservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.lawencon.mailservice.dto.User;
import com.lawencon.mailservice.service.ActiveMQProducerService;

@Service
public class ActiveMQProducerServiceImpl implements ActiveMQProducerService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void sendActiveMqMessage(User user) {
		jmsTemplate.convertAndSend("activeMQ-queue", user);
	}

}
