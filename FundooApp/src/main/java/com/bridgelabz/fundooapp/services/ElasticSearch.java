package com.bridgelabz.fundooapp.services;

import java.util.List;

import com.bridgelabz.fundooapp.model.NoteInformation;

public interface ElasticSearch {
	String CreateNote(NoteInformation note);

	String UpdateNote(NoteInformation note);

	String DeleteNote(NoteInformation note);

	

	List<NoteInformation> searchbytitle(String title);

}
