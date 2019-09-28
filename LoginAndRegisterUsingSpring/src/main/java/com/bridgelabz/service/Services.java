package com.bridgelabz.service;

import com.bridgelabz.model.PasswordUpdate;
import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.model.StudentRegistration;

public interface Services {
	boolean register(StudentRegistration registration);
	boolean login(StudentLogin dao );
	int updatePassword(PasswordUpdate update);

}
