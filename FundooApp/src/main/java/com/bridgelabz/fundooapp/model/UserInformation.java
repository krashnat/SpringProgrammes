package com.bridgelabz.fundooapp.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	long id;
	String name;
	String email;
	String password;
	Long mobNo;
	LocalDateTime createdDate;
	boolean isVerified;

}
