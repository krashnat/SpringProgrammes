package com.bridgelabz.fundooapp.services;

import com.bridgelabz.fundooapp.model.Note;
import com.bridgelabz.fundooapp.model.NoteDto;

public interface NoteService {

	boolean createNote(NoteDto information, String token);

	boolean updateNote(Note information, String token);

	boolean deleteNote(Note information, String token);
}
