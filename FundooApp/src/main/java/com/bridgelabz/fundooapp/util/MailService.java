package com.bridgelabz.fundooapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(String email, String url) {
		System.out.println("inside send mail");
		SimpleMailMessage message = new SimpleMailMessage();
		String from = "krashnat.cdr869@gmail.com";
		//message.setFrom(from);
		message.setTo(email);
		message.setSubject("Verifivation");
		message.setText(url);
		mailSender.send(message);
	}

}
