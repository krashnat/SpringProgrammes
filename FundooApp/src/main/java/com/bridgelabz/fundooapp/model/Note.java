package com.bridgelabz.fundooapp.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Note {
	private long id;
	private String title;
	private String description;
	private boolean isArchieved;
	private boolean isPinned;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime upDateAndTime;

}
