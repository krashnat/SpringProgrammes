package com.bridgelabz.fundooapp.services;

import com.bridgelabz.fundooapp.model.NoteUpdation;

import java.util.List;

import com.bridgelabz.fundooapp.model.NoteDto;
import com.bridgelabz.fundooapp.model.NoteInformation;

public interface NoteService {

	void createNote(NoteDto information, String token);

	void updateNote(NoteUpdation information, String token);

	void deleteNote(NoteUpdation information, String token);

	List<NoteInformation> getAllNotes(String token);
}
