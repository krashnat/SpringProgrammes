package com.bridgelabz.fundooapp.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReminderDto {
	private LocalDateTime reminder;
}
