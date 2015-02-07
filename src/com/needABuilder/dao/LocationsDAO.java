package com.needABuilder.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.County;
import com.needABuilder.entities.Locations;
import com.needABuilder.managedBeans.Util;
/*
 * 
 * Data Access Object class (DAO) for locations queries and JPA actions
 * 
 */

public class LocationsDAO {
	
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private static EntityManagerFactory factory;
	
	//Method to find current Business account
	public List<BusinessAccount> findCurrentAccount(){
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		List<BusinessAccount> businessAccount = em.createQuery("from BusinessAccount a", BusinessAccount.class)
				.getResultList();
		em.close();
		
		return businessAccount;
	}
	
	
	
	
}
