package com.lawencon.mailservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.mailservice.dto.User;
import com.lawencon.mailservice.service.ActiveMQProducerService;

@RestController
@RequestMapping("/api/v1/activemq")
public class ActiveMQController {
	private ActiveMQProducerService activeMQProducerService;

	public ActiveMQController(ActiveMQProducerService activeMQProducerService) {
		this.activeMQProducerService = activeMQProducerService;
	}

	@PostMapping("/sendMessage")
	public ResponseEntity<String> publishMessage(@RequestBody User user) {
		try {
			activeMQProducerService.sendActiveMqMessage(user);
			return new ResponseEntity<>("Sent to Active MQ Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
