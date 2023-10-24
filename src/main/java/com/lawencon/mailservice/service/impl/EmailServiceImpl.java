package com.lawencon.mailservice.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.mailservice.dto.ContentMail;
import com.lawencon.mailservice.service.EmailService;
import com.lawencon.mailservice.service.ThymeleafService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

//	@Value("${spring.mail.username}")
	private String fromEmail = "alghany@gmail.com";

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	ThymeleafService thymeleafService;

	@Override
	public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
		try {

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			Map<String, Object> variablesHTML = new HashMap<>();
			Map<String, Object> variables = new HashMap<>();
			variables.put("full_name", "Alif Nafis");
			variables.put("username", "emc02");
			variables.put("first_name", "Alif");
			variables.put("last_name", "Nafis");
			variables.put("email", "alghany@gmail.com");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			variables.put("date", sdf.format(new Date()));
			
			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setCc(cc);
			mimeMessageHelper.setSubject(subject);
			String HTMLData = body;
			String stringHtml = thymeleafService.initContent(HTMLData, variables);
			variablesHTML.put("WHOLE", stringHtml);
			mimeMessageHelper.setText(thymeleafService.createContent("templateTest.html", variablesHTML), true);
			if (file != null) {
				for (int i = 0; i < file.length; i++) {
					mimeMessageHelper.addAttachment(file[i].getOriginalFilename(),
							new ByteArrayResource(file[i].getBytes()));
				}
			}

			javaMailSender.send(mimeMessage);
			return "Mail Send";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String sendEmail(ContentMail body) {
		try {

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			Map<String, Object> variablesHTML = new HashMap<>();
			Map<String, Object> variables = new HashMap<>();
			variables.put("full_name", "Alif Nafis");
			variables.put("username", "emc02");
			variables.put("first_name", "Alif");
			variables.put("last_name", "Nafis");
			variables.put("email", "alghany@gmail.com");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			variables.put("date", sdf.format(new Date()));
			
			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(body.getTo());
			mimeMessageHelper.setCc(body.getCc());
			mimeMessageHelper.setSubject(body.getSubject());
			String HTMLData = body.getBody();
			String stringHtml = thymeleafService.initContent(HTMLData, variables);
			variablesHTML.put("WHOLE", stringHtml);
			mimeMessageHelper.setText(thymeleafService.createContent("templateTest.html", variablesHTML), true);

			javaMailSender.send(mimeMessage);
			return "Mail Send";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
