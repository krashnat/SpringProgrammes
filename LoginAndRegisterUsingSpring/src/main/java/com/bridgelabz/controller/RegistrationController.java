package com.bridgelabz.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.StudentRegistration;
import com.bridgelabz.service.Services;
import com.bridgelabz.util.Utility;

@Controller
public class RegistrationController {
	// invokes registration page
	@RequestMapping("/registrationpage")
	public String resistrationPage() {
		System.out.println("inside cont");
		return "Registration";

	}

	@Autowired
	private Services service;

	@Autowired
	private Utility utility;

	// method to registraton or save the user detail into the database
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView doregister(@ModelAttribute StudentRegistration student) {
		String password = student.getPassword();
		String ePass = utility.encryptPassword(password);
		System.out.println(ePass);
		student.setPassword(ePass);

		System.out.println(Pattern.matches("[789][0-9]{9}", student.getMobno()));
		if (Pattern.matches("[789][0-9]{9}", (student.getMobno()))) {
		
			boolean result=service.register(student);
			if (result) {
				// invokes login page after registration completion
				return new ModelAndView("RegistrationSuccess");
			}

			else {
				// invokes registration page if fails to registraton
				
				return new ModelAndView("Registration");
			}
		}

		else {

			return new ModelAndView("Registration");
		}

	}
}
