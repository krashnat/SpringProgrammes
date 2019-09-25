package com.bridgelabz.fundooapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.model.Note;
import com.bridgelabz.fundooapp.model.NoteDto;
import com.bridgelabz.fundooapp.responses.Response;
import com.bridgelabz.fundooapp.services.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService service;

	@PostMapping("/create")
	public ResponseEntity<Response> registration(@RequestBody NoteDto information,
			@RequestHeader("token") String token) {
		System.out.println(information.getDescription());
		boolean result = service.createNote(information, token);
		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created", 200));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failed to note created", 300));
		}

	}

	
	@PostMapping("/update")
	public ResponseEntity<Response> update(@RequestBody Note note,@RequestHeader("token") String token)
	{
		System.out.println("inside update controller"+note.getId());
		boolean result=service.updateNote(note, token);
		if(result)
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response("note updated", 200));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failed to note update", 300));
		}
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Response> delete(@RequestBody Note note,@RequestHeader("token") String token)
	{
		service.deleteNote(note, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note deleted", 200));
		
	}
	
	
	
}
