package com.bridgelabz.fundooapp.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundooapp.responses.MailObject;

@Component
public class MailServiceProvider {
	@Autowired
	private static JavaMailSender javaMailSender;

	public static void sendEmail(String toEmail, String subject, String body) {

		String fromEmail = "krashnat.cdr869@gmail.com";
		String password = "888888";

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(prop, auth);
		send(session, fromEmail, toEmail, subject, body);
	}

	private static void send(Session session, String fromEmail, String toEmail, String subject, String body) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, "Krishna"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (Exception e) {
			System.out.println("exception occured while sending mail");

		}
	}
	
	@RabbitListener(queues = "rmq.rube.queue")
	public void recievedMessage(MailObject employee) {
		
		
		sendEmail(employee.getEmail(),employee.getSubject(),employee.getMessage());
		System.out.println("Recieved Message From RabbitMQ: " + employee);
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * public void sendMail(MailObject object) {
	 * MailServiceProvider.sendEmail(object.getEmail(), object.getSubject(),
	 * object.getMessage());
	 * 
	 * 
	 * }
	 */
	
	
	
	
	
	
}
