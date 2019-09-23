package com.bridgelabz.fundooapp.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooapp.model.NoteInformation;

@Repository
public class NoteRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	public NoteInformation save(NoteInformation noterInformation) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(noterInformation);

		return noterInformation;

	}

}
