package com.bridgelabz.service;

import com.bridgelabz.model.PasswordUpdate;
import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.model.StudentRegistration;

public interface Services {
	int register(StudentRegistration registration);
	int login(StudentLogin dao );
	int updatePassword(PasswordUpdate update);

}
