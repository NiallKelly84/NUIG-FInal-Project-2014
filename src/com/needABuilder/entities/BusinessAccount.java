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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * 
 *Entity class for Business Account 
 * 
 */

@Entity
@Table(name = "business_accounts") //maps the class to the appropriate table in the database
public class BusinessAccount {

	//mapping of class attributes to the appropriate attributes in the database table
	@Id	//declare primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "surname")
	private String surname;

	@Column(name = "business_name")
	private String businessName;

	@Column(name = "telephone_number")
	private String telephoneNum;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "town")
	private String town;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name = "description")
	private String description;

	//Many to one relationship with County class
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "county_id", referencedColumnName = "id") })	//identifies attribute that the foreign key is mapped to
	private County county;
	
	
	//One to many relationship with Projects class
	@OneToMany(mappedBy = "businessAccount", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<Projects> projects;  //list of projects associated with each business account

	//One to many relationship with Locations class
	@OneToMany(mappedBy = "businessAccount", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<Locations> locations;  //list of locations associated with each business account
	
	//One to many relationship with Comment class
	@OneToMany(mappedBy = "businessAccount", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<Comment> comments;  //list of comments associated with each business account
	
	
	//Getters and setters for each attribute of the class
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getTelephoneNum() {
		return telephoneNum;
	}

	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
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

	public County getCounty() {
		if (county == null) {
			county = new County();
		}
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	//added last night
	public List<Projects> getProjects()
	{
		if (projects == null)
		{
			projects = new ArrayList<Projects>();
		}
		
		return projects;
	}
	
	public void setProjects(List<Projects> projects)
	{
		this.projects = projects;
	}
	
	
	public List<Locations> getLocations()
	{
		if (locations == null)
		{
			locations = new ArrayList<Locations>();
		}
		
		return locations;
	}

	public void setLocations(List<Locations> locations)
	{
		this.locations = locations;
	}
	//added last night
	
	public List<Comment> getComments()
	{
		if (comments == null)
		{
			comments = new ArrayList<Comment>();
		}
		
		return comments;
	}
	
	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}

}
