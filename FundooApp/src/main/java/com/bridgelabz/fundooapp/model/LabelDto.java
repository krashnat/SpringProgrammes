package com.bridgelabz.fundooapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class LabelDto {
	
	@Id
	private long id;
	private String name;
	

}
