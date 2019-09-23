package com.bridgelabz.fundooapp.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.model.NoteDto;
import com.bridgelabz.fundooapp.model.NoteInformation;
import com.bridgelabz.fundooapp.model.UserInformation;
import com.bridgelabz.fundooapp.repository.NoteRepository;
import com.bridgelabz.fundooapp.repository.UserRepository;
import com.bridgelabz.fundooapp.util.JwtGenerator;

@Service
public class NoteServiceImplementation implements NoteService {

	@Autowired
	private JwtGenerator tokenGenerator;

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserInformation user;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NoteInformation noteinformation;

	@Transactional
	@Override
	public boolean createNote(NoteDto information, String token) {
		try {
			System.out.println("in service");
			Long userid = (long) tokenGenerator.parseJWT(token);
			System.out.println("inside note service"+userid);
			
			user = repository.getUserById(userid);
			System.out.println("inside service"+user);
			if (user != null) {
				noteinformation = modelMapper.map(information, NoteInformation.class);
				noteinformation.setCreatedDateAndTime(LocalDateTime.now());
				noteinformation.setArchieved(false);
				noteinformation.setPinned(false);
				user.getNote().add(noteinformation);
				noteRepository.save(noteinformation);
				return true;
			}
			else
			{
				return false;
			}

		} catch (Exception e) {
			System.out.println("not exist user");
		}
		return false;
		

	}

}
