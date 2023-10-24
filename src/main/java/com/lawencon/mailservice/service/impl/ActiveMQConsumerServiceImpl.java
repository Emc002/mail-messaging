package com.lawencon.mailservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.lawencon.mailservice.dto.User;
import com.lawencon.mailservice.service.ActiveMQConsumerService;

@Service
public class ActiveMQConsumerServiceImpl implements ActiveMQConsumerService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMQConsumerServiceImpl.class);

    @JmsListener(destination = "activeMQ-queue")
    public void messageListener(User user) {
        LOGGER.info("Message received! {}", user);
    }
}
