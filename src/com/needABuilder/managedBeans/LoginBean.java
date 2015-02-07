package com.needABuilder.managedBeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.needABuilder.dao.LoginDAO;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.services.PasswordEncrypt;

/*
 * 
 * Managed Bean class for user login and logout
 * 
 */
@ManagedBean //Declares the class to be a managed bean and lists it in the persistence.xml
@RequestScoped   //Defines the scope of the managed bean
public class LoginBean implements Serializable {

	//declare class variables
	private static final long serialVersionUID = -3194865539528813474L;
	private String email;
	private String password;
	private PasswordEncrypt pw=new PasswordEncrypt();
	private LoginDAO l=new LoginDAO();
	

	public LoginBean() {
	}
	
	
	//method to allow user to login
	public String login(){
		password=pw.EncryptPass(password);	//pass the password to the encryption method
	
		try {
			BusinessAccount user=l.loginToDB(email, password);	//get the account matching this name and password
			if (email.equalsIgnoreCase(user.getEmail())
					&& password.equals(user.getPassword())) {	//if they match
			
				 HttpSession session = Util.getSession();
		            session.setAttribute("email", email);	//set the session email as this email address
		
				return "success";   
				//return this string for navigation purposes (used by faces-config.xml)
			}
			addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,	//otherwise display this message
					"Invalid credentials entered!", null));
			return "failure";

		} catch (Exception e) {	//display this message if an exception occurs
			addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"System error occured. Contact system admin!", null));
			return "systemError";
		}
	}
	
	//method to logout
    public String logout() {
      HttpSession session = Util.getSession();	//get the current session
      session.invalidate();	//destroy this session
      return "logout";
    //return this string for navigation purposes (used by faces-config.xml)
   }

	
    //method to add a faces message
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);	//takes the current message to be displayed
	}

	//getters and setters for attributes
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
