package com.bridgelabz.fundooapp.responses;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Component
public class MailObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String email;
	String subject;
	String message;
	

}
