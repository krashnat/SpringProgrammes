package com.bridgelabz.fundooapp.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundooapp.model.NoteInformation;

public interface CollaboratorService {
	
	
	NoteInformation addCollaborator(Long noteId, String email, String token);

	NoteInformation removeCollaborator(Long noteId, String email, String token);

	List<NoteInformation> getColabNotes(String token)
			throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException;

}
