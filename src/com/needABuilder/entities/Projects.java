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

@Entity
@Table(name = "projects")  //maps the class to the appropriate table in the database
public class Projects {

	//mapping of class attributes to the appropriate attributes in the database table
	@Id   //declare primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int project_id;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_description")
	private String projectDescription;
	
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

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}	
}
