package com.bridgelabz.fundooapp.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

	private String message;
	private int statusCode;
	
	public Response(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;

	}

	public Response(String message, int statusCode, Object obj) {
		this.message = message;
		this.statusCode = statusCode;
		

	}

}
