package com.bridgelabz.fundooapp.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
@Table(name = "usersdetail")
public class UserInformation {

	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String email;
	private String password;
	private Long mobNo;
	private LocalDateTime createdDate;
	private boolean isVerified;
	
	  @OneToMany(cascade=CascadeType.ALL) 
	  private List<NoteInformation> note;
	 
	

}
