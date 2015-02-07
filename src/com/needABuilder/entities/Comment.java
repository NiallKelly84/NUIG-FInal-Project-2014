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
 *Entity class for Comments 
 * 
 */
@Entity
@Table(name = "comments")  //maps the class to the appropriate table in the database
public class Comment {

	//mapping of class attributes to the appropriate attributes in the database table
	@Id  //declare primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comment_id;

	@Column(name = "comment_text")
	private String commentText;
	
	@Column(name = "date")
	private String commentdate;
	
	@Column(name = "commentname")
	private String commentname;
	
	//Many to one relationship with BusinessAccount class
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "contractor_id", referencedColumnName="id") })//identifies attribute that the foreign key is mapped to
	private BusinessAccount businessAccount;

	
	//getters and setters for each of the attributes
	public String getCommentname() {
		return commentname;
	}

	public void setCommentname(String commentname) {
		this.commentname = commentname;
	}
	
	public BusinessAccount getBusinessAccount() {
		if (businessAccount == null) {
			businessAccount = new BusinessAccount();
		}
		return businessAccount;
	}

	public void setBusinessAccount(BusinessAccount businessAccount) {
		this.businessAccount = businessAccount;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public String getCommentdate() {
		return commentdate;
	}

	public void setCommentdate(String commentdate) {
		this.commentdate = commentdate;
	}
	
}
	