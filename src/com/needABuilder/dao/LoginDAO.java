package com.needABuilder.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.needABuilder.entities.BusinessAccount;
/*
 * 
 * Data Access Object class (DAO) for project queries and JPA actions
 * 
 */
public class LoginDAO {
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private static EntityManagerFactory factory;

	//database call for user login
	public BusinessAccount loginToDB(String email, String password){
		//Query to find business account matching email and password passed in the argument
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query q = em
				.createQuery("SELECT u FROM BusinessAccount u WHERE u.email = :email AND u.password = :pass");
		q.setParameter("email", email);
		q.setParameter("pass", password);
		BusinessAccount user=(BusinessAccount)q.getSingleResult();
		return user;
	}
}
