package com.bridgelabz.fundooapp.services;

import com.bridgelabz.fundooapp.model.LoginInformation;
import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserDto;

public interface Services {

	boolean login(LoginInformation information);

	boolean register(UserDto ionformation);
	
	boolean update(PasswordUpdate information);

}
