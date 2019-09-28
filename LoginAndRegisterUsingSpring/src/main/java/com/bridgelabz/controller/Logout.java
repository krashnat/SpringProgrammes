package com.bridgelabz.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class Logout {
	@RequestMapping("/logout")
	public String resistrationPage()
	{
		System.out.println("inside Log outcont");
		return	"Login";
		
	}

}
