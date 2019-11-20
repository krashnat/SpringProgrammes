package com.bridgelabz.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.demo.ServiceImplement.ServiceProvider;
import com.bridgelabz.demo.userda.RegistrationDA;



@RestController
public class Registration {
	
	@Autowired
	private RegistrationDA information;
	
	@Autowired
	private ServiceProvider perform;
	
	@RequestMapping("/registration")
	public void register(@RequestBody RegistrationDA information)
	{
		
		
		perform.register(information);
		
	}

}
