package com.bridgelabz.fundooapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.model.LabelDto;
import com.bridgelabz.fundooapp.model.LabelInformation;
import com.bridgelabz.fundooapp.responses.Response;
import com.bridgelabz.fundooapp.services.LabelService;

@RestController
@RequestMapping("/label")
public class LabelController {

	@Autowired
	private LabelService service;

	@PostMapping("/create")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto label,@RequestHeader("token") String token) {
		System.out.println(label.getName());
		service.createLabel(label,token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("label created", 200));
	}

	@PostMapping("/addlabel")
	public void addLabel(@RequestBody LabelInformation label, @RequestHeader("token") String token) {
		
		
	}
}
