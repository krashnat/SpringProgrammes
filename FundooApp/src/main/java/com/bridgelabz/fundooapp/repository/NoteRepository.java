package com.bridgelabz.fundooapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

	public NoteInformation findById(long id)
	{
		System.out.println("in repository");
		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery("from NoteInformation where id=:id ");
		q.setParameter("id", id);
		return (NoteInformation)q.uniqueResult();
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<NoteInformation> getNotes(long userid)
	{
		System.out.println("in repository");
		Session session = entityManager.unwrap(Session.class);
	//	Query q=session.createQuery("from NoteInformation where user_id=:userid ");
		return session.createQuery("from NoteInformation where user_Id='"+userid+"'").getResultList();
		/*q.setParameter("id", userid);
		return (List<NoteInformation>) q.list();*/
		
		
	}

}
