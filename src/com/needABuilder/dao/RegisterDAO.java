package com.needABuilder.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.County;
import com.needABuilder.managedBeans.Util;
/*
 * 
 * Data Access Object class (DAO) for register queries and JPA actions
 * 
 */
public class RegisterDAO {
	//declare class variables (including persistence unit)
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private static EntityManagerFactory factory;
	private List<BusinessAccount> userList=new ArrayList<BusinessAccount>();

	//method to see if an account exists in the database already
	public List<BusinessAccount> checkIfExists(String registrationEmail) {
		//create query to see if any business accounts have an email
		//matching that passed in the argument
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query myQuery = em
				.createQuery("SELECT u FROM BusinessAccount u WHERE u.email=:email");
		myQuery.setParameter("email", registrationEmail);
		List<BusinessAccount> accounts = myQuery.getResultList();
		return accounts;
	}

	//method to add a businessAccount
	public void addAccount(BusinessAccount businessAccount) {
		//create entity manager object and persist account passed in the argument of the method
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(businessAccount);
		em.getTransaction().commit();
		em.close();
	}

	//get list of all counties in County table
	public List<County> getListOfCounties() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		List<County> counties = em.createQuery("from County c", County.class)
				.getResultList();
		em.close();
		return counties;
	}

	//database action to add company description to a business account
	//after a user has registered and logged in
	public void addCompanyDescription(String businessDescription,
			String sessionEmail) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//select business accounts matching the session email
		Query myQuery = em
				.createQuery("SELECT u FROM BusinessAccount u WHERE u.email=:email");
		myQuery.setParameter("email", sessionEmail);
		List<BusinessAccount> accounts = myQuery.getResultList();
		accounts.get(0).setDescription(businessDescription);	//set the description on this business account
		em.merge(accounts.get(0));	//merge this account to the database
		em.getTransaction().commit();
		em.close();
	}
	

	//get the user account for the current customer
	public List<BusinessAccount> accountForCurrentCustomer(){
	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	EntityManager em = factory.createEntityManager();	
	
		em.getTransaction().begin();
		String sessionEmail=Util.getEmail();
		Query myQuery = em.createQuery("SELECT u FROM BusinessAccount u WHERE u.email=:email");
		myQuery.setParameter("email", sessionEmail);
		userList=myQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		
		return userList;
	}

}
