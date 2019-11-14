package com.bridgelabz.fundooapp.repository;

import java.util.List;

import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserInformation;

public interface UserRepository {
	UserInformation save(UserInformation userInformation);
	UserInformation getUser(String email);
	
	
	boolean verify(Long id);
	boolean upDate(PasswordUpdate information, Long token);
	UserInformation getUserById(Long id );

	List<UserInformation> getUsers();

}
