package com.bridgelabz.fundooapp.repository;

import java.util.List;

import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserInformation;

public interface UserRepository {
	boolean save(UserInformation userInformation);
	UserInformation getUser(String email);
	
	boolean upDate(PasswordUpdate information);

}
