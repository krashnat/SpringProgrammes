package com.bridgelabz.fundooapp.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Component
@ToString
public class NoteInformation {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String description;
	private boolean isArchieved;
	private boolean isPinned;
	private boolean isTrashed;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime upDateAndTime;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "id")
	private List<LabelInformation> list;
	
	
}
