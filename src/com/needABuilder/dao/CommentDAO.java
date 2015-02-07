package com.needABuilder.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.Comment;
/*
 * 
 * Data Access Object class (DAO) for comment queries and JPA actions
 * 
 */
public class CommentDAO {
	
	private static EntityManagerFactory factory;
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private List<Comment> listOfComments;
	
	//method to get current business account
	public List<BusinessAccount> getBusinessAccount(int contractorId){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
			em.getTransaction().begin();
			Query myQuery = em.createQuery("SELECT u FROM BusinessAccount u WHERE u.id=:id");
			myQuery.setParameter("id", contractorId);
			List<BusinessAccount> accounts=myQuery.getResultList();
			
			return accounts;
	}
	
	//Method to add a comment to the database
	public void addCommentToDB(Comment comment){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
		em.getTransaction().begin();
		em.persist(comment);	//JPA action to add comment object from argument to the database
		em.getTransaction().commit();
		em.close();
	}
	
	//Method to view Comments associated with a contractor
	public List<Comment> viewCommentsFromDB(int contractorId){
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
			em.getTransaction().begin();
			//Query to find comments associated with the contractor id above
			Query myQuery = em.createQuery("SELECT c FROM Comment c INNER JOIN c.businessAccount b WHERE b.id=:id");
			myQuery.setParameter("id", contractorId);
			listOfComments=myQuery.getResultList();
			
			return listOfComments;
	}

}
