package com.needABuilder.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.needABuilder.dao.LocationsDAO;
import com.needABuilder.dao.RegisterDAO;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.County;
import com.needABuilder.entities.Locations;

/*
 * 
 * Managed Bean for Comments submitted by users
 * 
 */
@ManagedBean  //Declares the class to be a managed bean and lists it in the persistence.xml
@RequestScoped  //Defines the scope of the managed bean
public class LocationsBean implements Serializable{
 
	private static final long serialVersionUID = -2107387060867715013L;
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";	//declare persistence unit for persistence.xml
	
	//declare class variables
	private static EntityManagerFactory factory;
	private Locations locations;
	private List<Locations> locationsList = new ArrayList<Locations>();
	private List<BusinessAccount> businessAccount;
	private int locationId;
	private String county_name;
	private ArrayList<String> countyList=new ArrayList<String>();
	private LocationsDAO loc=new LocationsDAO();

	//method invoked when the bean is called for the first time
	@PostConstruct
	public void init() {
		locations = new Locations();
	}
	
	//method to find the current business account
	public LocationsBean() {
		businessAccount=loc.findCurrentAccount(); //make the database call to the LocationsDAO class
		setBusinessAccount(businessAccount);	//set the businessAccount object
	}
	
	//method to add work locations to a business account
	public String addLocation(ArrayList<String> countyList) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
			em.getTransaction().begin();
			
			//find current account for session user
			String sessionEmail=Util.getEmail();
			Query myQuery = em.createQuery("SELECT u FROM BusinessAccount u WHERE u.email=:email");
			myQuery.setParameter("email", sessionEmail);
			List<BusinessAccount> accounts=myQuery.getResultList();
	        BusinessAccount account =accounts.get(0);
	        
	        ArrayList<Locations> myLocations=new ArrayList<Locations>();
	        
	        //persist locations to the database.
	        Query myQuery2=em.createQuery("SELECT c FROM County c WHERE c.id=:id");
	        EntityManager entman[] = new EntityManager[countyList.size()];
	        for (int i=0; i<countyList.size(); i++){  //Iterate through the counties selected by the user 
	       //create entity manager objects for each iteration
	        	entman[i] = factory.createEntityManager();	
				entman[i].getTransaction().begin();
	        	
	        	String nextCounty=countyList.get(i);
	            myQuery2.setParameter("id", Integer.parseInt(nextCounty));
	            
	            List<County> myCounties=myQuery2.getResultList();
	            String countyFromList=myCounties.get(0).getName();	//get the county name for this iteration
	            locations.setBusinessAccount(account);
	            locations.setCountyName(countyFromList);	//set the county for this business account and iteration
	            entman[i].persist(locations);
	            entman[i].getTransaction().commit();
	            entman[i].close();	//end transaction
	        }   
	       
	        em.getTransaction().commit();
	        em.close();

	        return "success";	//string for faces-config.xml navigation
		}
	
	
	//get a list of locations for a business account
	public List<Locations> getLocationsList() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
		em.getTransaction().begin();
			
		//find the business account object for the current session user 
			String sessionEmail=Util.getEmail();
			Query myQuery = em.createQuery("SELECT u FROM BusinessAccount u WHERE u.email=:email");
			myQuery.setParameter("email", sessionEmail);
			List<BusinessAccount> userList=myQuery.getResultList();			
	        BusinessAccount account =userList.get(0);
	      
	        //This line refreshes the account object with the new values from the database (updates the view)
	        em.refresh(account);
	   
	        locationsList=account.getLocations();	//get the locations and add them to this list
			em.getTransaction().commit();
			em.close();
		
			return locationsList;		//return the list
		}
	
	//method to delete a location from the list
	public String deleteLocation(int id) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
		
			em.getTransaction().begin();	//begin transaction
			//find locations matching the location id selected by the user
			Query myQuery = em.createQuery("SELECT l FROM Locations l WHERE l.location_id=:locationId");
			myQuery.setParameter("locationId", id);
			List<Locations> currentLocation=myQuery.getResultList();
			String countyName=currentLocation.get(0).getCountyName();
			em.remove(currentLocation.get(0));	//remove this location from the database for this user
			em.getTransaction().commit();
			em.close();

			
			return "delete";  //string for faces-config.xml navigation
		} 

	//Getters and setters for attributes
	public Locations getLocations() {
		return locations;
	}
	
	public List<BusinessAccount> getBusinessAccount() {
		return businessAccount;
	}
	
	public void setBusinessAccount(List<BusinessAccount> businessAccount) {
		this.businessAccount = businessAccount;
	}


	public int getLocationId() {
		return locationId;
	}


	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}


	public String getCountyName() {
		return county_name;
	}


	public void setCountyName(String county_name) {
		this.county_name = county_name;
	}

	public ArrayList<String> getCountyList() {
		return countyList;
	}

	public void setCountyList(ArrayList<String> countyList) {
		this.countyList = countyList;
	}

}