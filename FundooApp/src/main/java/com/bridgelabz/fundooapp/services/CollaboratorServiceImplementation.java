package com.bridgelabz.fundooapp.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.NoteInformation;
import com.bridgelabz.fundooapp.model.UserInformation;
import com.bridgelabz.fundooapp.repository.NoteRepository;
import com.bridgelabz.fundooapp.repository.UserRepository;
import com.bridgelabz.fundooapp.util.JwtGenerator;

@Service
@Transactional
public class CollaboratorServiceImplementation implements CollaboratorService{

	@Autowired
	private JwtGenerator tokenGenerator;
	
	@Autowired
	private UserInformation userInformation;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Override
	public NoteInformation addCollaborator(Long noteId, String email, String  token) {

		UserInformation user;
		UserInformation  collaborator = repository.getUser(email);
		try {
			System.out.println("in service");
			Long userid = (long) tokenGenerator.parseJWT(token);
			System.out.println("inside note service" + userid);

			user = repository.getUserById(userid);
		}
		
		catch(Exception e) {
			throw new UserException("user is not present with the given id ");
		}
		if(user !=null) {
			if(collaborator !=null) {
				NoteInformation note=noteRepository.findById(noteId);
				note.getCollabList().add(collaborator);
				noteRepository.save(note);
				return note;
			}
			else {
				throw new UserException("user is not present with the given id ");
			}
		}
		else {
			throw new UserException("collorator not exist ");
		}
		
		
	}
	@Override
	public NoteInformation removeCollaborator(Long noteId, String email, String  token) {

		UserInformation user;
		UserInformation  collaborator = repository.getUser(email);
		try {
			System.out.println("in service");
			Long userid = (long) tokenGenerator.parseJWT(token);
			System.out.println("inside note service" + userid);

			user = repository.getUserById(userid);
		}
		
		catch(Exception e) {
			throw new UserException("user is not present with the given id ");
		}
		if(user !=null) {
			if(collaborator !=null) {
				NoteInformation note=noteRepository.findById(noteId);
				note.getCollabList().remove(collaborator);
				noteRepository.save(note);
				return note;
			}
			else {
				throw new UserException("user is not present with the given id ");
			}
		}
		else {
			throw new UserException("collorator not exist ");
		}
		
		
	}


}
