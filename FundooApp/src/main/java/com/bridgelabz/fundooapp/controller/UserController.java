package com.bridgelabz.fundooapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.model.LoginInformation;
import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserDto;
import com.bridgelabz.fundooapp.responses.Response;
import com.bridgelabz.fundooapp.services.Services;
import com.bridgelabz.fundooapp.util.MailService;

@RestController
@RequestMapping("/fundooapp")
public class UserController {


	@Autowired
	private Services service;

	@GetMapping("/registration")
	public ResponseEntity<Response> registration(@RequestBody UserDto information) {
		System.out.println("inside controller");
		boolean result = service.register(information);
		if (result) {
			Response response=new Response("registration success",200,information);
			
			return  new ResponseEntity<>(response, HttpStatus.OK);
			
		} else {
			Response response=new Response("registration fail",200,information);
			return  new ResponseEntity<>(response, HttpStatus.OK);
			
		}

	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginInformation information) {
		System.out.println("inside controller");
		boolean result = service.login(information);
		System.out.println(result);
		if (result) {
			HttpHeaders h=new HttpHeaders();
			h.add("login", "successfully done");
			Response response=new Response("login success",200);
			return  new ResponseEntity<>(response, h,HttpStatus.OK);
		} else {
			Response response=new Response("login fail",404);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		}

	}
	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestBody PasswordUpdate update)
	{
		boolean result=service.update(update);
		if(result)
		{
			Response response=new Response("password updated",200);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		}
		else
		{
			Response response=new Response("check password again",200,update);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		}
		
	}
	@PostMapping("/email")
	public String sendMail(@RequestBody PasswordUpdate update)
	{
		MailService service=new MailService();
		System.out.println("inside mail");
		System.out.println(update.getUsername());
		service.sendMail(update.getUsername(), "hi");
		return "success";
		
	}

}
