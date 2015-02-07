package com.needABuilder.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.needABuilder.dao.ProjectDAO;
import com.needABuilder.dao.RegisterDAO;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.Projects;

/*
 * 
 * Managed Bean class for user login and logout
 * 
 */
@ManagedBean //Declares the class to be a managed bean and lists it in the persistence.xml
@SessionScoped   //Defines the scope of the managed bean
public class ProjectBean implements Serializable {

	//declare class variables
	private static final long serialVersionUID = -2107387060867715013L;
	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private static EntityManagerFactory factory;
	private Projects projects;
	private List<BusinessAccount> businessAccount;
	private int projectId;
	private String projectName;
	private String projectDescription;
	private RegisterDAO reg = new RegisterDAO();
	private ProjectDAO pro = new ProjectDAO();

	//method invoked when the bean is called for the first time
	@PostConstruct
	public void init() {
		projects = new Projects();
	}
	
	//constructor
	public ProjectBean() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		List<BusinessAccount> businessAccount = em.createQuery(
				"from BusinessAccount a", BusinessAccount.class)
				.getResultList();	//query to return business account
		em.close();
		setBusinessAccount(businessAccount); //set this business account
	}

	//method to add project
	public String addProject() {
		String sessionEmail = Util.getEmail();	//get session email
		businessAccount = reg.checkIfExists(sessionEmail);	//check if this email exists already
		BusinessAccount account = businessAccount.get(0);	//max of 1 result-get this result
		projects.setBusinessAccount(account);	//set this business account to the project object
		account.getProjects().add(projects);	//manage both sides
		pro.addProjectToDB(projects);	//add this project object to the database (Using ProjectDAO class)

		return "success";
		//return this string for navigation purposes (used by faces-config.xml)
	}
	
	//method to delete a project from the database 
	public String deleteProject(int id) {	//pass a project id to this class
		pro.deleteProjectFromDB(id);	//call method from ProjectDAO class

		return "delete";
		//return this string for navigation purposes (used by faces-config.xml)
	}

	//method to get a list of projects for a user
	public List<Projects> getProjectList() {
		String sessionEmail = Util.getEmail();	//get current user email
		businessAccount = reg.checkIfExists(sessionEmail);	//check if this account exists already
		BusinessAccount account = businessAccount.get(0);	//get first account on list
		List<Projects> projectList = new ArrayList<Projects>();	//create an array list of project objects
		projectList=pro.getProjectListFromDB(account);	//call this method from the ProjectDAO class and pass the current user account

		return projectList; //return the list of projects for the current user

	}

	//method to pass parameters between JSF pages
	public String displayProjectDetails() {
		//FacesContext object created and parameters are set. These parameters can be received by another JSF page
		FacesContext fc = FacesContext.getCurrentInstance();
		this.setProjectId(Integer.parseInt(getProjIdParam(fc)));
		this.setProjectName(getProjNameParam(fc));
		this.setProjectDescription(getProjDescriptionParam(fc));

		return "success";
	}

	//Get the parameter for project id
	public String getProjIdParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("projId");

	}

	//Get the parameter for project description
	public String getProjDescriptionParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("projDescription");

	}

	//Get the parameter for project name
	public String getProjNameParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("projName");

	}

	//set the parameter for project name
	private void setProjectName(String projNameParam) {
		this.projectName = projNameParam;
	}

	//set the parameter for project description
	private void setProjectDescription(String projDescriptionParam) {
		this.projectDescription = projDescriptionParam;
	}

	//getters and setters
	public String getProjectName() {
		return projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public Projects getProjects() {
		return projects;
	}

	public List<BusinessAccount> getBusinessAccount() {
		return businessAccount;
	}

	public void setBusinessAccount(List<BusinessAccount> businessAccount) {
		this.businessAccount = businessAccount;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

}