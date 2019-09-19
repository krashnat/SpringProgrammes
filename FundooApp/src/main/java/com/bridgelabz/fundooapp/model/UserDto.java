package com.bridgelabz.fundooapp.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserDto {
	String name;
	String email;
	String password;
	Long mobNo;

}
