package com.bridgelabz.fundooapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.model.NoteDto;
import com.bridgelabz.fundooapp.model.NoteInformation;
import com.bridgelabz.fundooapp.model.NoteUpdation;
import com.bridgelabz.fundooapp.responses.Response;
import com.bridgelabz.fundooapp.services.NoteService;

@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = { "token" })
@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService service;

	@PostMapping("/create")
	public ResponseEntity<Response> registration(@RequestBody NoteDto information,
			@RequestHeader("token") String token) {
		System.out.println(information.getDescription());
		service.createNote(information, token);

		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("registration success", 200, information));

	}

	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestBody NoteUpdation note, @RequestHeader("token") String token) {
		System.out.println("inside update controller" + note.getId());
		service.updateNote(note, token);

		return ResponseEntity.status(HttpStatus.OK).body(new Response("note updated", 200));

	}
	@PostMapping("/archieve/{id}")
	public ResponseEntity<Response> archieve(@PathVariable long id, @RequestHeader("token") String token) {
		System.out.println("inside delete controller" + id);
		service.archievNote(id, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note archieced", 200));

	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable long id, @RequestHeader("token") String token) {
		System.out.println("inside delete controller" + id);
		service.deleteNote(id, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note deleted", 200));

	}
	
	@DeleteMapping("/deletenote/{id}")
	public ResponseEntity<Response> deletePermenently(@PathVariable long id, @RequestHeader("token") String token) {
		System.out.println("inside delete controller" + id);
		service.deleteNotePemenetly(id, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("note deleted", 200));

	}
	
	
	

	@GetMapping("/fetchNote")
	public ResponseEntity<Response> getAllNotes(@RequestHeader("token") String token) {

		List<NoteInformation> notes = service.getAllNotes(token);

		return ResponseEntity.status(HttpStatus.OK).body(new Response("reqired notes are", 200, notes));
	}

	@GetMapping("/fetchTrashedNote")
	public ResponseEntity<Response> getTrashedNotes(@RequestHeader("token") String token) {
		List<NoteInformation> notes = service.getTrashedNotes(token);

		return ResponseEntity.status(HttpStatus.OK).body(new Response("reqired notes are", 200, notes));

	}
	
	@GetMapping("/fetcharchivenote")
	public ResponseEntity<Response> getArchiveNote(@RequestHeader("token") String token) {
		List<NoteInformation> notes = service.getArchiveNote(token);

		return ResponseEntity.status(HttpStatus.OK).body(new Response("reqired notes are", 200, notes));

	}
	
	
	

}
