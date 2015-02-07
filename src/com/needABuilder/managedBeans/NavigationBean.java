package com.needABuilder.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/*
 * 
 * used by faces-config.xml for navigation
 * 
 */

@ManagedBean
@RequestScoped
public class NavigationBean {
	
	public String Login(){
		return "login";
	}
	
	public String Search(){
		return "search";
	}
	
	

}
