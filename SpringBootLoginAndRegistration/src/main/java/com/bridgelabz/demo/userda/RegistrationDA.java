package com.bridgelabz.demo.userda;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class RegistrationDA {
	String name;
	String email;
	String mobNo;
	String password;

}
