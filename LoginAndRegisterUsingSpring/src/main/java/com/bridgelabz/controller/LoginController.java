package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.service.Services;
import com.bridgelabz.util.Utility;
@Controller
public class LoginController {
	//invokes login page
	@RequestMapping("/loginpage")
	public String loginPage()
	{
		System.out.println("inside cont");
		return	"Login"; 
	}

	//dependency injection for Service class
	@Autowired
	private Services service;
	//dependency injection for Service class
	@Autowired
	 private Utility utility;
	//Method of login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView dolLogin(@ModelAttribute StudentLogin student)
	{
		System.out.println("inside login");
		System.out.println("inside controller"+" "+student.getEmail());
		String password=student.getPassword();
		String ePass=utility.encryptPassword(password);
		student.setPassword(ePass);
		boolean result=service.login(student);
		if (result)
		{
			//invokes success page
		 return new ModelAndView("LoginSuccess");	
		}
		else 
		{
			//invokes logi page if fails to login
			return new ModelAndView("Login");
		}
		
	}
	
	
}
