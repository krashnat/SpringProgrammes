package com.bridgelabz.fundooapp.responses;

import com.bridgelabz.fundooapp.model.NoteDto;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class NoteResponse {
	
	private NoteDto note;
	
	public  NoteResponse(NoteDto note) {
		this.note=note;
	
	}

}
