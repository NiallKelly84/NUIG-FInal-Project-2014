package com.needABuilder.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.Projects;
/*
 * 
 * Data Access Object class (DAO) for project queries and JPA actions
 * 
 */
public class ProjectDAO {
	//declare class variables (including persistence unit)
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private static EntityManagerFactory factory;

	//method to add project to the database
	public void addProjectToDB(Projects projects) {
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(projects); //add project object passed in the argument to the database 
		em.getTransaction().commit();
		em.close();
	}
	
	//method to delete project from the database
	public void deleteProjectFromDB(int id){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		//query the database to find project matching the id passed in the argument
		em.getTransaction().begin();
		Query myQuery = em
				.createQuery("SELECT p FROM Projects p WHERE p.project_id=:projectId");
		myQuery.setParameter("projectId", id);
		List<Projects> currentProject = myQuery.getResultList();

		em.remove(currentProject.get(0));	//call the JPA remove method to remove this instance of Project
		em.getTransaction().commit();
		em.close();
		
	}
	
	//Method to get projects associated with a business account
	public List<Projects> getProjectListFromDB(BusinessAccount account){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		List<Projects> projectList = new ArrayList<Projects>();

		em.getTransaction().begin();
		projectList = account.getProjects();
		em.getTransaction().commit();
		em.close();
		
		return projectList;
	}
	
}
