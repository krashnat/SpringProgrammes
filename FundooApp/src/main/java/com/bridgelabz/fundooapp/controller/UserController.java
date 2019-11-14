package com.bridgelabz.fundooapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.model.LoginInformation;
import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserDto;
import com.bridgelabz.fundooapp.model.UserInformation;
import com.bridgelabz.fundooapp.responses.Response;
import com.bridgelabz.fundooapp.responses.UsersDetail;
import com.bridgelabz.fundooapp.services.Services;
import com.bridgelabz.fundooapp.util.JwtGenerator;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/fundooapp")
public class UserController {

	@Autowired
	private Services service;
	
	@Autowired
	private JwtGenerator generate;
	
	

	@PostMapping("/registration")
	public ResponseEntity<Response> registration(@RequestBody UserDto information) {

		boolean result = service.register(information);
		if (result) {
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("registration success", 200, information));

		} else {
                   
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new Response("user already exist", 400, information));

		}

	}

	@PostMapping("/login")
	public ResponseEntity<UsersDetail> login(@RequestBody LoginInformation information) {
		System.out.println("inside controller");
		UserInformation userInformation = service.login(information);

		if (userInformation!=null) {
			String token=generate.jwtToken(userInformation.getUserId());
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("login", information.getUsername())
					.body(new UsersDetail(token, 200, information));

		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UsersDetail("Login fail", 400, information));
		}

	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<Response> userVerification(@PathVariable("token") String token) throws Exception {

		System.out.println("token for verification" + token);
		boolean update = service.verify(token);
		if (update) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("verified", 200));
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("not verified", 400));

		}

	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<Response> forgogPassword(@RequestParam("email") String email) {
		System.out.println(email);

		boolean result = service.isUserExist(email);
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("user exist", 200));
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("user not exist", 400));
		}

	}

	@PutMapping("/update/{token}")
	public ResponseEntity<Response> update(@PathVariable("token") String token, @RequestBody PasswordUpdate update) {
		System.out.println("inside controller  " +update.getConfirmPassword());
		System.out.println("inside controller  " +token);
		boolean result = service.update(update, token);
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("password updated successfully", 200, update));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response("Entered password not matches", 401, update));

		}

	}
	
	@GetMapping("/getusers")
	public ResponseEntity<Response> getUsers(){
		List<UserInformation> users=service.getUsers();
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("password updated successfully", 200, users));
		
	}
	

}
