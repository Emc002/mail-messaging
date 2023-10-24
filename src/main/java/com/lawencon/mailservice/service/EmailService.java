package com.lawencon.mailservice.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.mailservice.dto.ContentMail;

public interface EmailService {
	String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body);
	String sendEmail(ContentMail body);
}
