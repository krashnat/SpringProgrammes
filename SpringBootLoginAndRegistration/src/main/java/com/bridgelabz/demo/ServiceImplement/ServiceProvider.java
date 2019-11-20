package com.bridgelabz.demo.ServiceImplement;



import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.demo.model.RegistrationInformation;
import com.bridgelabz.demo.repository.UserRepository;
import com.bridgelabz.demo.userda.RegistrationDA;

@Component
public class ServiceProvider  {

	@Autowired
	private RegistrationInformation finformation;
	@Autowired
	private UserRepository repository;
	

	public void register( RegistrationDA information) 
	{
		finformation.setName(information.getName());
		finformation.setEmail(information.getEmail());
		finformation.setPassword(information.getPassword());
		finformation.setMobNo(information.getPassword());
		finformation.setCreatedDate(LocalDateTime.now());
		RegistrationInformation result=repository.save(finformation);
		System.out.println(result);
	}

	
}
