package com.bridgelabz.fundooapp.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.NoteUpdation;
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
	public void createNote(NoteDto information, String token) {
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

			} else {
				throw new UserException("note is not present with the given id ");
			}

		} catch (Exception e) {

			throw new UserException("user is not present with the given id ");
		}

	}

	@Transactional
	@Override
	public void updateNote(NoteUpdation information, String token) {
		try {
			Long userid = (long) tokenGenerator.parseJWT(token);
			System.out.println("inside note service" + userid);

			user = repository.getUserById(userid);

			NoteInformation note = noteRepository.findById(information.getId());
			if (note != null) {
				System.out.println("note is   " + note);
				note.setId(information.getId());
				note.setDescription(information.getDescription());
				note.setTitle(information.getTitle());
				note.setPinned(information.isPinned());
				note.setArchieved(information.isArchieved());
				note.setArchieved(information.isTrashed());
				note.setUpDateAndTime(LocalDateTime.now());
				noteRepository.save(note);
			} else {
				throw new UserException("note is not present");
			}

		} catch (Exception e) {
			throw new UserException("user is not present");
		}

	}

	@Transactional
	@Override
	public void deleteNote(long id, String token) {
		NoteInformation note = noteRepository.findById(id);
		//note.setTrashed(true);
		note.setTrashed(!note.isTrashed());
		noteRepository.save(note);

	}
	@Transactional
	@Override
	public void archievNote(long id, String token) {
		NoteInformation note = noteRepository.findById(id);
		//note.setTrashed(true);
		note.setArchieved(!note.isArchieved());
		noteRepository.save(note);

	}

	@Transactional
	@Override
	public boolean deleteNotePemenetly(long id, String token) {
		NoteInformation note = noteRepository.findById(id);
		if (note != null) {
			return noteRepository.deleteNote(id);
		} else {
			throw new UserException("note is not present");
		}

	}

	@Override
	public List<NoteInformation> getAllNotes(String token) {
		try {
			Long userId = (long) tokenGenerator.parseJWT(token);
			user = repository.getUserById(userId);

			if (user != null) {
				System.out.println(user);
				List<NoteInformation> list = noteRepository.getNotes(userId);
				System.out.println("note fetched is" + " " + list.get(0));
				return list;

			} else {
				System.out.println(user + "hello");
				throw new UserException("note is not  present");
			}

		} catch (Exception e) {
			throw new UserException("exception occured");
		}

	}

	@Override
	public List<NoteInformation> getTrashedNotes(String token) {
		try {
			Long userId = (long) tokenGenerator.parseJWT(token);
			user = repository.getUserById(userId);

			if (user != null) {
				System.out.println(user);
				List<NoteInformation> list = noteRepository.getTrashedNotes(userId);
				System.out.println("note fetched is" + " " + list.get(0));
				return list;

			} else {
				System.out.println(user + "hello");
				throw new UserException("note is not  present");
			}

		} catch (Exception e) {
			throw new UserException("exception occured");
		}

	}
	
	@Override
	public List<NoteInformation> getArchiveNote(String token) {
		try {
			Long userId = (long) tokenGenerator.parseJWT(token);
			user = repository.getUserById(userId);

			if (user != null) {
				System.out.println(user);
				List<NoteInformation> list = noteRepository.getArchiveNotes(userId);
				System.out.println("note fetched is" + " " + list.get(0));
				return list;

			} else {
				System.out.println(user + "hello");
				throw new UserException("note is not  present");
			}

		} catch (Exception e) {
			throw new UserException("exception occured");
		}

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
	// noteRepository.save(updatedNotes);

}
