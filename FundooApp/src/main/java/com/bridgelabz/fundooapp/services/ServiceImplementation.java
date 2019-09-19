package com.bridgelabz.fundooapp.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.model.LoginInformation;
import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserDto;
import com.bridgelabz.fundooapp.model.UserInformation;
import com.bridgelabz.fundooapp.repository.UserRepository;

@Service
public class ServiceImplementation implements Services {

	@Autowired
	private UserInformation userInformation;

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder encryption;

	@Transactional
	@Override
	public boolean register(UserDto information) {
		System.out.println("inside service");
		userInformation.setName(information.getName());
		userInformation.setEmail(information.getEmail());
		userInformation.setMobNo(information.getMobNo());
		userInformation.setCreatedDate(LocalDateTime.now());
		String epassword = encryption.encode(information.getPassword());
		userInformation.setPassword(epassword);
		userInformation.setVerified(true);
		return repository.save(userInformation);

	}

	@Override
	public boolean login(LoginInformation information) {
		UserInformation user = repository.getUser(information.getUsername());
		System.out.println("inside service " + user);
		if (user != null) {

			if (encryption.matches(information.getPassword(), user.getPassword())) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	@Override
	public boolean update(PasswordUpdate information) {
		if (information.getNewPassword().equals(information.getConfirmPassword())) {
			return repository.upDate(information);
		}
		else
		{
			return false;
		}

	}

}
