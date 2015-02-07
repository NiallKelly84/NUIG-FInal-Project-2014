
package com.needABuilder.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.needABuilder.dao.RegisterDAO;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.County;
import com.needABuilder.services.PasswordEncrypt;
/*
 * 
 * Managed Bean class for user registration
 * 
 */
@ManagedBean //Declares the class to be a managed bean and lists it in the persistence.xml
@ViewScoped   //Defines the scope of the managed bean
public class RegisterBean implements Serializable {

	//declare class variables
	private static final long serialVersionUID = -2107387060867715013L;
	private List<BusinessAccount> userList=new ArrayList<BusinessAccount>();
	private BusinessAccount businessAccount;
	private List<County> counties;
	private PasswordEncrypt pw=new PasswordEncrypt();
	private RegisterDAO reg=new RegisterDAO();

	
	public RegisterBean() {

		setCounties(reg.getListOfCounties());
	}

	//method invoked when the bean is called for the first time
	@PostConstruct
	public void init() {
		businessAccount = new BusinessAccount();
	}

	//method to create account. The registration email is passed as an argument
	public String create(String registrationEmail) {
	//Check if email entered already exists in the database
	 List<BusinessAccount> accounts=reg.checkIfExists(registrationEmail);
		
	 //if no account exists already
		if (accounts.size()==0) {
			String encryptedPass=pw.EncryptPass(businessAccount.getPassword());	//encrypt the password
			businessAccount.setPassword(encryptedPass);	//set this encrypted password
			reg.addAccount(businessAccount);	//add this business account to the database (using method in RegisterDAO)
			addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,	//display this message
					"User Registration Successful!", null));
			return "success";
			//return this string for navigation purposes (used by faces-config.xml)
		} 
		else {
			//otherwise, display this message
			addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"User Registration Failed! This email address is already registered", null));
			return "register";
			//return this string for navigation purposes (used by faces-config.xml)
		}
	}
	

	//Method to update account after company description is entered
	public String update() {
		
		//get session email
		String sessionEmail=Util.getEmail();
		//add this description using method in RegisterDAO class	
		reg.addCompanyDescription(businessAccount.getDescription(),sessionEmail);
			
		return "success";
	}
	
	//method to return current user account for welcome page 
	public List<BusinessAccount> welcomeDisplay() {
		userList=reg.accountForCurrentCustomer();
		return userList;
	}
	
	
	//methods for navigation in faces-config.xml
	public String navigateToDescription(){
		return "description";
	}
	
	public String navigateToLocations(){
		return "locations";
	}
	
	public String navigateToProjects(){
		return "projects";
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	//getters and setters
	public BusinessAccount getBusinessAccount() {
		return businessAccount;
	}
	
	public void setBusinessAccount(BusinessAccount b){
		this.businessAccount=b;
	}

	public List<County> getCounties() {
		return counties;
	}

	public void setCounties(List<County> counties) {
		this.counties = counties;
	}

	public List<BusinessAccount> getUserList() {
		return userList;
	}

	public void setUserList(List<BusinessAccount> userList) {
		this.userList = userList;
	}

	
}
