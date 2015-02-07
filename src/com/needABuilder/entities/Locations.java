package com.needABuilder.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * 
 *Entity class for Work Locations that business accounts
 *are available to work in
 * 
 */

@Entity
@Table(name = "work_locations")  //maps the class to the appropriate table in the database
public class Locations {

	//mapping of class attributes to the appropriate attributes in the database table
	@Id  //declare primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int location_id;

	@Column(name = "name")
	private String county_name;
	
	//Many to one relationship with BusinessAccount class
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "contractor_id", referencedColumnName="id") })  //identifies attribute that the foreign key is mapped to
	private BusinessAccount businessAccount;
	
	//Getters and setters for each attribute of the class
	public BusinessAccount getBusinessAccount() {
		if (businessAccount == null) {
			businessAccount = new BusinessAccount();
		}
		return businessAccount;
	}

	public void setBusinessAccount(BusinessAccount businessAccount) {
		this.businessAccount = businessAccount;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int loctaion_id) {
		this.location_id = loctaion_id;
	}

	public String getCountyName() {
		return county_name;
	}

	public void setCountyName(String county_name) {
		this.county_name = county_name;
	}
}