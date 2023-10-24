package com.lawencon.mailservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.mailservice.dto.ContentMail;
import com.lawencon.mailservice.service.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailSendController {

	private EmailService emailService;

	public EmailSendController(EmailService emailService) {
		this.emailService = emailService;
	}

	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}

	@PostMapping("/send")
	public String sendmail(@RequestParam(value = "file", required = false) MultipartFile[] file, String to, String[] cc,
			String subject, String body) {
		return emailService.sendMail(file, to, cc, subject, body);
	}

	@PostMapping("/send-email")
	public String sendEmail(@RequestBody ContentMail body) {
		return emailService.sendEmail(body);
	}
}
