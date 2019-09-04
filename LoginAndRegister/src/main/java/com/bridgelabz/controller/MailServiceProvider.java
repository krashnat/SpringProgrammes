package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.PasswordUpdate;
import com.bridgelabz.model.StudentRegistration;
import com.bridgelabz.service.Services;
import com.bridgelabz.util.Utility;

@Controller
public class MailServiceProvider {

	@Autowired
	private Utility util;

	@Autowired
	private Services service;

	@Autowired
	private MailSender mailSender;

	@RequestMapping("/Gmail")
	public String mailPageView() {

		return "Gmail";

	}

	@RequestMapping(value = "/GmailService", method = RequestMethod.POST)
	public ModelAndView doSendMail(@ModelAttribute StudentRegistration information) {
		System.out.println("inside controller");
		System.out.println("new pass" + information.getEmail());
		String to = information.getEmail();
		System.out.println("inside mail sender");
		SimpleMailMessage message = new SimpleMailMessage();
		String from = "krashnat.cdr869@gmail.com";
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("test");
		String herelisalinktochangepassword="http://localhost:8080/LoginAndRegister/ForgotPassword";
		message.setText(herelisalinktochangepassword);
		mailSender.send(message);
		return new ModelAndView("MailNotification");
		

	}
	@RequestMapping("/ForgotPassword")
	public String passwordUpdate() {

		return "ForgotPassword";

	}
	@RequestMapping(value = "/ForgottePassword", method = RequestMethod.POST)
	public ModelAndView doUpdatePassword(@ModelAttribute PasswordUpdate information) {
		System.out.println(information.getConfirmPassword());
		boolean result1 = information.getNewPassword().equals(information.getConfirmPassword());
		System.out.println(result1);
		if (information.getNewPassword().equals(information.getConfirmPassword())) {
			System.out.println("inside update password controller");
			String password = util.encryptPassword(information.getNewPassword());
			information.setNewPassword(password);
			int result = service.updatePassword(information);
			{
				if (result > 0) {
					return new ModelAndView("Login");
				} else {
					return new ModelAndView("ForgotPassword");
				}
			}
		}

		return new ModelAndView("ForgotPassword");
	}

}
