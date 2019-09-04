package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.model.StudentRegistration;
import com.bridgelabz.serviceimplement.Services;
import com.bridgelabz.util.Utility;
@Controller
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
	public ModelAndView dolLogin(@ModelAttribute StudentLogin student)
	{
		System.out.println("inside login");
		System.out.println("inside controller"+" "+student.getEmail());
		String password=student.getPassword();
		String ePass=utility.encryptPassword(password);
		student.setPassword(ePass);
		int result=service.login(student);
		if (result>0)
		{
		 return new ModelAndView("LoginSuccess");	
		}
		else 
		{
			return new ModelAndView("index");
		}
		
	}
	
	
}
