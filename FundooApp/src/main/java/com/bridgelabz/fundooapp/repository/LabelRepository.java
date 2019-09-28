package com.bridgelabz.fundooapp.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooapp.model.LabelInformation;

@Repository
public class LabelRepository {
	
	
	@Autowired
	private EntityManager entityManager;

	public LabelInformation save(LabelInformation labelInformation) {

		Session session = entityManager.unwrap(Session.class);
		session.save(labelInformation);
		return labelInformation;
	}

	
	

}
