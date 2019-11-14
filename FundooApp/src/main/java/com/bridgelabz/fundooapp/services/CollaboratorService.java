package com.bridgelabz.fundooapp.services;

import com.bridgelabz.fundooapp.model.NoteInformation;

public interface CollaboratorService {
	
	
	NoteInformation addCollaborator(Long noteId, String email, String token);

	NoteInformation removeCollaborator(Long noteId, String email, String token);

}
