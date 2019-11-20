package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.StudentRegistration;
import com.bridgelabz.service.Services;
import com.bridgelabz.util.Utility;

@RestController
public class RegistrationController {
	@RequestMapping("/Registration")
	public String resistrationPage() {
		System.out.println("inside cont");
		return "Registration";

	}

	@Autowired
	Services service;

	@Autowired
	Utility utility;

	@RequestMapping(value = "/registrationpage", method = RequestMethod.POST)
	public String doregister(@RequestParam String email,@RequestParam String password,@RequestParam String mobno,@RequestParam String name) {
		StudentRegistration student=new StudentRegistration();
		
		if(email==null)
		{
			System.out.println("inside if");
			return "fail";
		}
		else
		{
			System.out.println("inside fail");
		student.setEmail(email);
		student.setMobno(mobno);
		student.setName(name);
		student.setPassword(password);
		//String password = student.getPassword();
		String ePass = utility.encryptPassword(password);
		System.out.println(ePass);
		student.setPassword(ePass);
		int result = service.register(student);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
		}

	}
}
