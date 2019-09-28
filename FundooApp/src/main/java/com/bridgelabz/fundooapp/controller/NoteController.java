package com.bridgelabz.fundooapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.model.NoteUpdation;
import com.bridgelabz.fundooapp.model.NoteDto;
import com.bridgelabz.fundooapp.model.NoteInformation;
import com.bridgelabz.fundooapp.responses.NoteResponse;
import com.bridgelabz.fundooapp.responses.Response;
import com.bridgelabz.fundooapp.services.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService service;

	@PostMapping("/create")
	public ResponseEntity<NoteResponse> registration(@RequestBody NoteDto information,
			@RequestHeader("token") String token) {
		System.out.println(information.getDescription());
		service.createNote(information, token);

		return ResponseEntity.status(HttpStatus.CREATED).body(new NoteResponse(information));

	}

	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestBody NoteUpdation note, @RequestHeader("token") String token) {
		System.out.println("inside update controller" + note.getId());
		service.updateNote(note, token);

		return ResponseEntity.status(HttpStatus.OK).body(new Response("note updated", 200));

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> delete(@RequestBody NoteUpdation note, @RequestHeader("token") String token) {
		service.deleteNote(note, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note deleted", 200));

	}
	
	@ PostMapping("/fetchNote")
	public ResponseEntity<Response> getAllNotes(@RequestHeader("token") String token)
	{
		
		List<NoteInformation> notes=service.getAllNotes(token);
		
		return ResponseEntity.status(HttpStatus.OK).body(new Response("reqired notes are", 200,notes));
	}
	
	
	
	
	

}
