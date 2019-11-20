package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.service.Services;
import com.bridgelabz.util.Utility;
@RestController
public class LoginController {

	@RequestMapping("/Login")
	public String resistrationPage()
	{
		System.out.println("inside cont");
		return	"Login"; 
	}


	@Autowired
	Services service;
	
	@Autowired
	Utility utility;
	@RequestMapping(value = "/LoginPage", method = RequestMethod.POST)
	public String dolLogin(@RequestParam String email,@RequestParam String password)
	{
		System.out.println("inside login");
		////System.out.println("inside controller"+" "+student.getEmail());
		//String password=student.getPassword();
		String ePass=utility.encryptPassword(password);
		//student.setPassword(ePass);
		StudentLogin student=new StudentLogin();
		student.setEmail(email);
		student.setPassword(ePass);
		int result=service.login(student);
		if (result>0)
		{
		return "success";
		}
		else 
		{
			return "Fail";
		}
		
	}
	
	
}
