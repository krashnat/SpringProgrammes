package com.bridgelabz.fundooapp.services;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.LoginInformation;
import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserDto;
import com.bridgelabz.fundooapp.model.UserInformation;
import com.bridgelabz.fundooapp.repository.UserRepository;
import com.bridgelabz.fundooapp.responses.MailResponse;
import com.bridgelabz.fundooapp.util.JwtGenerator;
import com.bridgelabz.fundooapp.util.MailServiceProvider;

@Service
public class ServiceImplementation implements Services {

	@Autowired
	private UserInformation userInformation;

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder encryption;

	@Autowired
	private JwtGenerator generate;

	@Autowired
	private MailResponse response;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public boolean register(UserDto information) {
		System.out.println("inside service");
		UserInformation user = repository.getUser(information.getEmail());

		if (user == null) {

			userInformation = modelMapper.map(information, UserInformation.class);

			userInformation.setCreatedDate(LocalDateTime.now());
			String epassword = encryption.encode(information.getPassword());
			userInformation.setPassword(epassword);
			userInformation.setVerified(false);
			userInformation = repository.save(userInformation);
			System.out.println("id" + " " + userInformation.getUserId());
			System.out.println("token" + " " + generate.jwtToken(userInformation.getUserId()));
			String mailResponse = response.formMessage("http://localhost:8080/fundooapp/verify",
					generate.jwtToken(userInformation.getUserId()));

			MailServiceProvider.sendEmail("krashnat.cdr869@gmail.com", "verification", mailResponse);

			return true;

		} else {
			throw new UserException("user already present");
			
		}

	}

	@Transactional
	@Override
	public boolean login(LoginInformation information) {
		UserInformation user = repository.getUser(information.getUsername());
		System.out.println("inside service " + user);
		if (user != null) {

			if ((user.isVerified() == true) && encryption.matches(information.getPassword(), user.getPassword())) {
				System.out.println(generate.jwtToken(user.getUserId()));
				return true;
			} else {
				String mailResponse = response.formMessage("http://localhost:8080/fundooapp/verify",
						generate.jwtToken(user.getUserId()));

				MailServiceProvider.sendEmail("krashnat.cdr869@gmail.com", "verification", mailResponse);

				return false;
			}

		} else {
			return false;
			
		}

	}

	@Transactional
	@Override
	public boolean update(PasswordUpdate information,String token) {
		if (information.getNewPassword().equals(information.getConfirmPassword())) {
			
			Long id = null;
			try {
				System.out.println( "in update method"+"   "+generate.parseJWT(token));
				id = (long) generate.parseJWT(token);
			} catch (JWTVerificationException e) {
				
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			return repository.upDate(information,id);
		} else {
			return false;
		}

	}

	public String generateToken(Long id) {
		return generate.jwtToken(id);

	}

	@Transactional
	@Override
	public boolean verify(String token) throws Exception {
		System.out.println("id in verification" + (long) generate.parseJWT(token));
		Long id = (long) generate.parseJWT(token);
		repository.verify(id);
		return true;
	}

	@Override
	public boolean isUserExist(String email) {
		UserInformation user = repository.getUser(email);
		if (user != null && user.isVerified() == true) {
			String mailResponse = response.formMessage("http://localhost:8080/fundooapp/verify",
					generate.jwtToken(user.getUserId()));
			MailServiceProvider.sendEmail("krashnat.cdr869@gmail.com", "verification", mailResponse);
			return true;
		}
		else
		{
			return false;
		}

		
	}

	
}
