package com.lawencon.mailservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.mailservice.dto.User;
import com.lawencon.mailservice.service.JsonKafkaProducerService;
import com.lawencon.mailservice.service.KafkaProducerService;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaJsonMessageController {

	private JsonKafkaProducerService jsonKafkaProducerService;
	private KafkaProducerService kafkaProducerService;

	public KafkaJsonMessageController(JsonKafkaProducerService jsonKafkaProducerService,
			KafkaProducerService kafkaProducerService) {
		this.jsonKafkaProducerService = jsonKafkaProducerService;
		this.kafkaProducerService = kafkaProducerService;
	}
	

	
	//this was not use JSON
	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("message") String message) {
		kafkaProducerService.sendMessage(message);
		return ResponseEntity.ok("Message Succesfull Send into Topic ");
	}

	@PostMapping("/sendMessage")
	public ResponseEntity<String> publish(@RequestBody User user) {
		jsonKafkaProducerService.sendMessage(user);
		return ResponseEntity.ok("Json message sent to kafka topic");
	}
}
