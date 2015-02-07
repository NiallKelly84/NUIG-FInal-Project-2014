package com.needABuilder.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 
 * Entity class for Administrator Account
 * 
 */

@Entity
@Table(name = "admin_accounts")	//map class to the appropriate entity (entity=table) in the database
public class AdminAccount {

	@Id	//declare primary key value
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;

	@Column(name = "email")	//map email attribute to appropriate attribute in the database
	private String email;

	@Column(name = "password") //map password attribute to appropriate attribute in the database
	private String password;
	
	
	//Getters and setters for attributes of the class
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
