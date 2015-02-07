package com.needABuilder.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.County;

/*
 * 
 * Data Access Object class (DAO) for search queries and JPA actions
 * 
 */
public class SearchDAO {

	//declare class variables (including persistence unit)
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private static EntityManagerFactory factory;
	private List<BusinessAccount> contractorList = new ArrayList<BusinessAccount>();

	//method to make database call to return the selected county name based on the county id passed
	public String showSelectedCounty(int countyId) {
		//create entity manager object
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		//find County objects matching this id
		em.getTransaction().begin();
		Query myQuery = em
				.createQuery("SELECT c FROM County c WHERE c.id=:CountyId");
		myQuery.setParameter("CountyId", countyId);
		List<County> myList = new ArrayList<County>();
		myList = myQuery.getResultList();	//create a list of counties (will only contain max of 1 value)
		String countyName = myList.get(0).getName();	//get county name associated with first value 
		em.getTransaction().commit();
		em.close();
		return countyName;
	}

	//method to list business accounts with work location matching a county name passed in argument
	public List<BusinessAccount> listCompaniesInSearchArea(String countyName) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		//query to select business accounts with work locations matching the county name
		em.getTransaction().begin();
		Query myQuery2 = em
				.createQuery("SELECT b FROM BusinessAccount b INNER JOIN b.locations l WHERE l.county_name=:CountyName");
		myQuery2.setParameter("CountyName", countyName);
		contractorList = myQuery2.getResultList();		//create a list of business accounts
		em.getTransaction().commit();
		em.close();

		return contractorList;
	}
	
	//list all business accounts in the database (used by administrator account only)
	public List<BusinessAccount> listAllCompaniesFromDB(){
		return contractorList;
	}
}
