package com.bridgelabz.fundooapp.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDetail {
	private String token;
	private int statuscode;
	 private Object obj;
	
public UsersDetail(String token,int statuscode,Object obj)
{
	this.token=token;
	this.statuscode=statuscode;
	this.obj=obj;
}
}
