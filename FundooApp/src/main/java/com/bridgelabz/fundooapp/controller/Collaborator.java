package com.bridgelabz.fundooapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.model.NoteInformation;
import com.bridgelabz.fundooapp.responses.Response;
import com.bridgelabz.fundooapp.services.CollaboratorService;

@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = { "token" })
@RestController
@RequestMapping("/collaborator")
public class Collaborator {
	@Autowired
	private CollaboratorService service;
	
	@PostMapping("/addCollab")
	public ResponseEntity<Response> addCollaborator(@RequestParam("noteId") Long noteId,@RequestParam("email") String email,
			@RequestHeader("token") String token) {
		//System.out.println(noteId);
		NoteInformation note=service.addCollaborator(noteId, email, token);
		System.out.println(note.getCollabList().toString());
	

		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("collaborator added", 200, note));

	}
	

	@PostMapping("/removeCollab")
	public ResponseEntity<Response> removeCollaborator(@RequestParam("noteId") Long noteId,@RequestParam("email") String email,
			@RequestHeader("token") String token) {
		//System.out.println(noteId);
		NoteInformation note=service.removeCollaborator(noteId, email, token);
		System.out.println(note.getCollabList().toString());
	

		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("collaborator removed", 200, note));

	}

}
