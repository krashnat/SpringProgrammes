package com.bridgelabz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dao.StudentDao;
import com.bridgelabz.model.PasswordUpdate;
import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.model.StudentRegistration;
//class implements service method 
@Service
public class Registration implements Services {
	@Autowired
	private StudentDao dao;

	public void setDao(StudentDao dao) {
		this.dao = dao;
	}

	public boolean register(StudentRegistration registration) {
		boolean temp = dao.register(registration);
		return temp;
	}
	

	public boolean login(StudentLogin login) {
		boolean result = dao.login(login);

		return result;

	}


	public int updatePassword(PasswordUpdate information) {
		int result=dao.updatePassword(information);
		return result;
	}
	

}
