package com.bridgelabz.fundooapp.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Component
public class NoteInformation {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String description;
	private boolean isArchieved;
	private boolean isPinned;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime upDateAndTime;
	
	
}
