package com.bridgelabz.fundooapp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PasswordUpdate {
	
	String username;
	String newPassword;
	String confirmPassword;

}
