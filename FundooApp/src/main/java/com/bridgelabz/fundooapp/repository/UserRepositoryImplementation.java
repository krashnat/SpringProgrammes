package com.bridgelabz.fundooapp.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooapp.model.PasswordUpdate;
import com.bridgelabz.fundooapp.model.UserInformation;

@Repository
public class UserRepositoryImplementation implements UserRepository {

	@Autowired
	private EntityManager entityManager;
	

	
	boolean result=false;
	
	@Override
	
	public boolean save(UserInformation userInformation) {

		Session session = entityManager.unwrap(Session.class);

		
		session.saveOrUpdate(userInformation);
		 result=true;
		return result;
		
		}

	@Override
	public UserInformation getUser(String email) {
		Session session = entityManager.unwrap(Session.class);
		
		Query q=session.createQuery(" FROM UserInformation where email=:email");  
		q.setParameter("email",email);  
	   return (UserInformation) q.uniqueResult();
	
	}

	@Override
	@Transactional
	public boolean upDate(PasswordUpdate information) {
		Session session = entityManager.unwrap(Session.class);
		session.beginTransaction();
		Query q=session.createQuery("update UserInformation set password=:p" +" " +" "+  "where email=:i");  
		q.setParameter("p",information.getNewPassword());  
		q.setParameter("i",information.getUsername());  
		  
		int status=q.executeUpdate();
		session.getTransaction().commit();
		if(status>0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	
		
	}

	


}
