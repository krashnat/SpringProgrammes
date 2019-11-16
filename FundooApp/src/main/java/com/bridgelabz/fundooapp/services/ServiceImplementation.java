package com.bridgelabz.fundooapp.services;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundooapp.configure.RabbitMQSender;
import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.LoginInformation;
import com.bridgelabz.fundooapp.model.NoteInformation;
import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserDto;
import com.bridgelabz.fundooapp.model.UserInformation;
import com.bridgelabz.fundooapp.reddisrepository.ReddisRepository;
import com.bridgelabz.fundooapp.repository.UserRepository;
import com.bridgelabz.fundooapp.responses.MailObject;
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

	@Autowired
	private MailObject mailObject;

	@Autowired
	private RabbitMQSender rabbitMQSender;

	@Autowired
	private ReddisRepository reddisRepository;

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
			// reddisRepository.save(userInformation);
			System.out.println("id" + " " + userInformation.getUserId());
			System.out.println("token" + " " + generate.jwtToken(userInformation.getUserId()));
			String mailResponse = response.formMessage("http://localhost:3000/verify",
					generate.jwtToken(userInformation.getUserId()));

			mailObject.setEmail("krashnat.cdr869@gmail.com");
			mailObject.setMessage(mailResponse);
			mailObject.setSubject("verification");

			rabbitMQSender.send(mailObject);
			return true;

		} else {
			throw new UserException("user already present");

		}

	}

	@Transactional
	@Override
	public UserInformation login(LoginInformation information) {
		UserInformation user = repository.getUser(information.getUsername());
		System.out.println("inside service " + user);
		if (user != null) {

			if ((user.isVerified() == true) && encryption.matches(information.getPassword(), user.getPassword())) {
				System.out.println(generate.jwtToken(user.getUserId()));
				return user;
			} else {
				String mailResponse = response.formMessage("http://localhost:3000/verify",
						generate.jwtToken(user.getUserId()));

				MailServiceProvider.sendEmail("krashnat.cdr869@gmail.com", "verification", mailResponse);

				return null;
			}

		} else {
			return null;

		}

	}

	@Transactional
	@Override
	public boolean update(PasswordUpdate information, String token) {
		if (information.getNewPassword().equals(information.getConfirmPassword())) {

			Long id = null;
			try {
				System.out.println("in update method" + "   " + generate.parseJWT(token));
				id = (long) generate.parseJWT(token);
				String epassword = encryption.encode(information.getConfirmPassword());
				information.setConfirmPassword(epassword);
				return repository.upDate(information, id);
			} catch (Exception e) {
				throw new UserException("user is not valid");
			}

		}

		else {
			throw new UserException("password not matches");
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
		try {
			UserInformation user = repository.getUser(email);
			if (user.isVerified() == true) {
				String mailResponse = response.formMessage("http://localhost:3000/updatePassword",
						generate.jwtToken(user.getUserId()));
				MailServiceProvider.sendEmail("krashnat.cdr869@gmail.com", "verification", mailResponse);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new UserException("User not exist");
		}
	}
      
	@Transactional
	@Override
	public List<UserInformation> getUsers() {
		List<UserInformation> users = repository.getUsers();
		UserInformation user = users.get(0);
		List<NoteInformation> note = user.getColaborateNote();
		// System.out.println(note.get(0).getId());
		return users;
	}

	
	@Transactional
	@Override
	public UserInformation getSingleUser(String token) {
		Long id;
		try {
			 id = (long) generate.parseJWT(token);
		} catch (Exception e) {

			throw new UserException("User not exist");
		}
		
		UserInformation user=repository.getUserById(id);
		System.out.println(user.getColaborateNote().toString());
		return null;
	}

	// MailServiceProvider.sendEmail("krashnat.cdr869@gmail.com", "verification",
	// mailResponse);

//		System.out.println("in service"+user.getEmail());
//		if (user != null) {
//			String mailResponse = response.formMessage("http://localhost:3000/updatePassword",
//					generate.jwtToken(user.getUserId()));
//			MailServiceProvider.sendEmail("krashnat.cdr869@gmail.com", "verification", mailResponse);
//			return true;
//		}
//		else
//		{
//			return false;
//		}

}
