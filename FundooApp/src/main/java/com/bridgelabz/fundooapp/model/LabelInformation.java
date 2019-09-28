package com.bridgelabz.fundooapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
@Entity
public class LabelInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int labelId;
	private String name;
	private long userId;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "labelId")
	private List<NoteInformation> list;
	

}
