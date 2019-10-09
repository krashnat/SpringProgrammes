package com.bridgelabz.fundooapp.repository;

import javax.persistence.EntityManager;

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

	@Override

	public UserInformation save(UserInformation userInformation) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(userInformation);

		return userInformation;

	}

	@Override
	public UserInformation getUser(String email) {
		Session session = entityManager.unwrap(Session.class);

		Query q = session.createQuery(" FROM UserInformation where email=:email");
		q.setParameter("email", email);

		return (UserInformation) q.uniqueResult();

	}

	@Override
	public UserInformation getUserById(Long id) {
		Session session = entityManager.unwrap(Session.class);

		Query q = session.createQuery(" FROM UserInformation where id=:id");
		q.setParameter("id", id);

		return (UserInformation) q.uniqueResult();

	}

	@Override

	public boolean upDate(PasswordUpdate information, Long id) {
		Session session = entityManager.unwrap(Session.class);

		Query q = session.createQuery("update UserInformation set password=:p" + " " + " " + "where id=:i");
		q.setParameter("p", information.getConfirmPassword());
		q.setParameter("i", id);

		int status = q.executeUpdate();

		if (status > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override

	public boolean verify(Long id) {
		Session session = entityManager.unwrap(Session.class);

		Query q = session.createQuery("update UserInformation set is_verified=:p" + " " + " " + "where id=:i");
		q.setParameter("p", true);
		q.setParameter("i", id);

		int status = q.executeUpdate();
		// session.getTransaction().commit();
		if (status > 0) {
			return true;
		} else {
			return false;
		}

	}

}
