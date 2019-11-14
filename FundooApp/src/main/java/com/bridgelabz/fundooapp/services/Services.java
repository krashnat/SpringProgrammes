package com.bridgelabz.fundooapp.services;

import java.util.List;

import com.bridgelabz.fundooapp.model.LoginInformation;
import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserDto;
import com.bridgelabz.fundooapp.model.UserInformation;

public interface Services {

	UserInformation login(LoginInformation information);

	boolean register(UserDto ionformation);
	
	

	boolean verify(String token) throws Exception;
	
	boolean isUserExist(String email);

	boolean update(PasswordUpdate information, String token);

	List<UserInformation> getUsers();

	

	

}
