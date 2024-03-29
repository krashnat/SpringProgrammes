package com.bridgelabz.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.PasswordUpdate;
import com.bridgelabz.serviceimplement.Services;
import com.bridgelabz.util.Utility;
@Controller
public class ForgottPassword {
	@RequestMapping("/ForgotPassword")
	public String resistrationPage()
	{
	
		return	"ForgotPassword";
		
	}
	@Autowired
	Utility util;
	
	@Autowired
	Services service;
	@RequestMapping(value = "/ForgottePassword", method = RequestMethod.POST)
	public ModelAndView doregister(@ModelAttribute PasswordUpdate information)
	{
		System.out.println("inside controller");
		System.out.println("new pass"+information.getNewPassword());
		System.out.println(information.getConfirmPassword());
		boolean result1=information.getNewPassword().equals(information.getConfirmPassword());
		System.out.println(result1);
		if(information.getNewPassword().equals(information.getConfirmPassword()))
		{
			System.out.println("inside update password controller");
			String password=util.encryptPassword(information.getNewPassword());
			information.setNewPassword(password);
		    int result=service.updatePassword(information);
		    {
		    	if (result>0)
				{
				 return new ModelAndView("Login");	
				}
				else 
				{
					return new ModelAndView("ForgotPassword");
				}
		    }
		}
		
			return new ModelAndView("ForgotPassword");	
		
		
	}
	}


