package com.bridgelabz.fundooapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.bridgelabz.fundooapp.responses.Response;
import com.bridgelabz.fundooapp.services.Services;

@RestController
@RequestMapping("/fundooapp")
public class UserController {

	@Autowired
	private Services service;

	@PostMapping("/registration")
	public ResponseEntity<Response> registration(@RequestBody UserDto information) {

		boolean result = service.register(information);
		if (result) {

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("registration success", 200, information));

		} else {

			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new Response("user already exist", 200, information));

		}

	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginInformation information) {
		System.out.println("inside controller");
		boolean result = service.login(information);

		if (result) {
			HttpHeaders h = new HttpHeaders();
			h.add("login", "successfully done");

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Login success", 200, information));

		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Login fail", 400, information));
		}

	}

	@GetMapping("/verify/{token}")
	public String userVerification(@PathVariable("token") String token) throws Exception {

		System.out.println("token for verification" + token);
		boolean update = service.verify(token);
		if (update) {
			return "verified";
		} else {
			return "register again";

		}

	}

	@PostMapping("/forgotpassword")
	public String forgogPassword(@RequestParam("email") String email) {
		if (service.isUserExist(email)) {
			return "exist";
		} else {
			return "not exist";
		}

	}

	@PutMapping("/update/{token}")
	public ResponseEntity<Response> update(@PathVariable("token") String token, @RequestBody PasswordUpdate update) {
		boolean result = service.update(update, token);
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("password updated successfully", 200, update));
		} else {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("Entered password not matches", 401, update));

		}

	}

}
