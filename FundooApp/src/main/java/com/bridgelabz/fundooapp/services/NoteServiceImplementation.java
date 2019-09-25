package com.bridgelabz.fundooapp.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.Note;
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
			System.out.println("inside note service" + userid);

			user = repository.getUserById(userid);
			System.out.println("inside service" + user);
			if (user != null) {
				noteinformation = modelMapper.map(information, NoteInformation.class);
				noteinformation.setCreatedDateAndTime(LocalDateTime.now());
				noteinformation.setArchieved(false);
				noteinformation.setPinned(false);
				noteinformation.setTrashed(false);
				user.getNote().add(noteinformation);
				noteRepository.save(noteinformation);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new UserException("the given id is not present");
		}

	}

	@Transactional
	@Override
	public boolean updateNote(Note information, String token) {
		try {
			long id = (long) tokenGenerator.parseJWT(token);

			NoteInformation note=noteRepository.findById(information.getId());
            note.setId(information.getId());
			note.setDescription(information.getDescription());
			note.setTitle(information.getTitle());
			note.setPinned(information.isPinned());
			note.setArchieved(information.isArchieved());
			note.setUpDateAndTime(LocalDateTime.now());
			noteRepository.save(note);
			return true;

		} catch (Exception e) {
			throw new UserException("the given id is not present");
		}

	}
	
	
	@Transactional
	@Override
	public boolean deleteNote(Note information,String token)
	{
		NoteInformation note =noteRepository.findById(information.getId());
		note.setTrashed(true);
		noteRepository.save(note);
		return true;
	}
	
	
	
	
	
	
	/*
	 * NoteInformation updatedNotes =
	 * noteRepository.findById(information.getId()).map(currentNote -> {
	 * currentNote.setTitle(information != null ? information.getTitle() :
	 * currentNote.getTitle()); currentNote.setDescription(information != null ?
	 * information.getTitle() : currentNote.getDescription());
	 * 
	 * return currentNote; }).orElseThrow(() -> new
	 * UserException("note not present"));
	 */
	//noteRepository.save(updatedNotes);


}
