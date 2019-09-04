package com.bridgelabz.serviceimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dao.StudentDao;
import com.bridgelabz.model.PasswordUpdate;
import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.model.StudentRegistration;

@Service
public class Registration implements Services {
	@Autowired
	StudentDao dao;

	public void setDao(StudentDao dao) {
		this.dao = dao;
	}

	public int register(StudentRegistration registration) {
		int temp = dao.register(registration);
		return temp;
	}
	

	public int login(StudentLogin login) {
		int result = dao.login(login);

		return result;

	}

	@Override
	public int updatePassword(PasswordUpdate information) {
		int result=dao.updatePassword(information);
		return result;
	}
	

}
