package com.needABuilder.managedBeans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/*
 * 
 *  class to generate session email
 *  
 */
 
public class Util {
 
	//method to get current session
      public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().getExternalContext().getRequest();
      }
 
      //method to get session email
      public static String getEmail()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("email").toString();
      }
      
}