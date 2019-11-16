package com.bridgelabz.fundooapp.services;

import com.bridgelabz.fundooapp.model.NoteUpdation;
import com.bridgelabz.fundooapp.model.ReminderDto;

import java.util.List;

import com.bridgelabz.fundooapp.model.NoteDto;
import com.bridgelabz.fundooapp.model.NoteInformation;

public interface NoteService {

	void createNote(NoteDto information, String token);

	void updateNote(NoteUpdation information, String token);

	void deleteNote(long id, String token);

	List<NoteInformation> getAllNotes(String token);

	List<NoteInformation> getTrashedNotes(String token);

	boolean deleteNotePemenetly(long id, String token);

	void archievNote(long id, String token);

	List<NoteInformation> getArchiveNote(String token);

	void addColour(Long noteId, String token, String colour);
	void addReminder(Long noteId,String token,ReminderDto reminder);
	void removeReminder(Long noteId,String token,ReminderDto reminder);

	void pin(long id, String token);

	List<NoteInformation> searchByTitle(String title);
	
}
