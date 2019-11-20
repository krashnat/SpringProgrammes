package com.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.service.Services;
import com.bridgelabz.util.Utility;



@RestController

@RequestMapping("/rest")
public class LoginController {
	
	@Autowired
	Services service;
	
	@Autowired
	Utility utility;

	@RequestMapping("/Login")
	public String resistrationPage()
	{
		System.out.println("inside cont");
		return	"Login"; 
	}

	@RequestMapping(value = "/LoginPage", method = RequestMethod.POST)
	public String dolLogin(@RequestParam String email,@RequestParam String password)
	{
		System.out.println("inside login");
		//System.out.println("inside controller"+" "+student.getEmail());
		//String password=student.getPassword();
		String ePass=utility.encryptPassword(password);
		StudentLogin student=new StudentLogin();
		student.setPassword(ePass);
		student.setEmail(email);
		int result=service.login(student);
		if (result>0)
		{
		 return "Success";
		}
		else 
		{
			return "Fail";
		}
		
	}
	
	@GetMapping("/test")
	public String test(@RequestParam String name)
	{
		return name;
	}
	
	
}
