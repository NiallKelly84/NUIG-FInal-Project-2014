package com.needABuilder.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * 
 *Entity class for Counties 
 * 
 */
@Entity
@Table(name = "counties")   //maps the class to the appropriate table in the database
public class County {

	//mapping of class attributes to the appropriate attributes in the database table
	@Id   //declare primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	//One to many relationship with BusinessAccount class
	@OneToMany(mappedBy = "county", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<BusinessAccount> businessAccounts;  //list of business accounts associated with each county
	
	//getters and setters for each of the attributes
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<BusinessAccount> getBusinessAccounts()
	{
		if (businessAccounts == null)
		{
			businessAccounts = new ArrayList<BusinessAccount>();
		}
		
		return businessAccounts;
	}

	public void setBusinessAccounts(List<BusinessAccount> businessAccounts)
	{
		this.businessAccounts = businessAccounts;
	}
}
