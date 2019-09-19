package com.bridgelabz.fundooapp.responses;

public class MailResponse {
	
	String url;
	String token;
	public String formMessage(String url,String toke)
	{
		
		return url+"/"+token;
	}

}
