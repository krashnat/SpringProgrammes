package com.bridgelabz.fundooapp.services;

import com.bridgelabz.fundooapp.model.NoteDto;

public interface NoteService {
	
	boolean createNote(NoteDto information,String token) ;
}
