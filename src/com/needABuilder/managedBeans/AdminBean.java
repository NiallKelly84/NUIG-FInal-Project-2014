package com.needABuilder.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import com.needABuilder.entities.AdminAccount;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.Locations;
import com.needABuilder.entities.Projects;

/*
 * 
 * Managed Bean class for administrator actions
 * 
 */
@ManagedBean    //Declares the class to be a managed bean and lists it in the persistence.xml
@SessionScoped  //Defines the scope of the managed bean
public class AdminBean implements Serializable {

	//declare class variables
	private static final long serialVersionUID = -3194865539528813474L;
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private static EntityManagerFactory factory;
	private String email;
	private String password;
	private int countyId;
	private List<BusinessAccount> contractorList = new ArrayList<BusinessAccount>();
	private List<BusinessAccount> myList = new ArrayList<BusinessAccount>();
	private int companyId;
	private String companyName;
	private List<Locations> locationsList = new ArrayList<Locations>();
	private List<Projects> projectList = new ArrayList<Projects>();
	

	public AdminBean() {
	}

	//Method for administrator to login to system
	public String adminlogin() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		//Query to select user account where email and password entered by the user
		Query q = em
				.createQuery("SELECT u FROM AdminAccount u WHERE u.email = :email AND u.password = :pass");
		q.setParameter("email", email);
		q.setParameter("pass", password);
		try {
			AdminAccount user = (AdminAccount) q.getSingleResult();
			if (email.equalsIgnoreCase(user.getEmail())
					&& password.equals(user.getPassword())) {	//If passwords match,
			
				 HttpSession session = Util.getSession();	//create session
		            session.setAttribute("email", email);
		
				return "success";
			}
			addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Invalid credentials entered!", null));
			return "failure";

		} catch (Exception e) {
			addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"System error occured. Contact system admin!", null));
			return "systemError";
		}
	}

	//Method for to log out of system
    public String logout() {
      HttpSession session = Util.getSession();	//get the current session
      session.invalidate();	//destroy the session
      return "logout";
   }
	
    //Method to delete user accounts
    public String deleteAccount(int companyId) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
		
		//select business account matching the company id submitted in the argument
			em.getTransaction().begin();
			Query myQuery = em.createQuery("SELECT b FROM BusinessAccount b WHERE b.id=:id");
			myQuery.setParameter("id", companyId);
			List<BusinessAccount> accountList=myQuery.getResultList();
			
			em.remove(accountList.get(0));	//remove this account using JPA
			em.getTransaction().commit();
			em.close();
			
			return "delete";
		} 
    
    //Method to search for all contractors in the system
    public List<BusinessAccount> searchAllContractors() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
	
			em.getTransaction().begin();
			Query myQuery = em.createQuery("SELECT b FROM BusinessAccount b");
			contractorList=myQuery.getResultList();
		    em.getTransaction().commit();
		    em.close();
			
			return contractorList;
		} 

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	//Getters and setters
	public void setLocationsList(List<Locations> locationsList) {
		this.locationsList = locationsList;
	}

	public List<Locations> getLocationsList() {

		return locationsList;
	}

	public List<Projects> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Projects> projectList) {
		this.projectList = projectList;
	}

	public int getCountyId() {
		return countyId;
	}

	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}

	public List<BusinessAccount> getContractorList() {
		return contractorList;
	}

	public void setContractorList(List<BusinessAccount> contractorList) {
		this.contractorList = contractorList;
	}

	public List<BusinessAccount> getMyList() {
		return myList;
	}

	public void setMyList(List<BusinessAccount> myList) {
		this.myList = myList;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
