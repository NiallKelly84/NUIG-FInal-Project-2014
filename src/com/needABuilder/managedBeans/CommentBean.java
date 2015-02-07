package com.needABuilder.managedBeans;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.needABuilder.dao.CommentDAO;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.Comment;
import com.needABuilder.services.CurrentDate;

/*
 * 
 * Managed Bean for Comments submitted by users
 * 
 */

@ManagedBean	//Declares the class to be a managed bean and lists it in the persistence.xml
@SessionScoped	//Defines the scope of the managed bean
public class CommentBean {
	
	//declare class variables
	private Comment comment;
	private Comment copyOfComment;
	private List<BusinessAccount> businessAccount;
	private List<Comment> listOfComments;
	private CommentDAO com = new CommentDAO();
	
	
	//method invoked when the bean is called for the first time
	@PostConstruct
	public void init() {
		comment = new Comment();
	}
	
	//method to add a new comment to a businessAccount
	public String addComment(int contractorId) {
		List<BusinessAccount> accounts=com.getBusinessAccount(contractorId);	//make a call to the CommentDAO class
		BusinessAccount account =accounts.get(0);	//only one value can be returned
		 CurrentDate d=new CurrentDate();	//create date object
	        String date=d.calculateCurrentDateTime();  //current date and time
	        comment.setCommentdate(date);//sets the current date for the message
	        comment.setBusinessAccount(account); //managing both sides
	        account.getComments().add(comment); //managing both sides
	        com.addCommentToDB(comment);	//make the call to the method in the CommentDAO class
			
			return "success1";	
			//return this string for navigation purposes (used by faces-config.xml)
		}
	
	//method to view comments associated with a business account
	public List<Comment> viewComments(int contractorId){
		listOfComments=com.viewCommentsFromDB(contractorId);	//make call to CommentDAO class
		Collections.reverse(listOfComments);//reverse the arraylist to display the newest comment at the top of the list
		
			return listOfComments;
	}
	
 //getters and setters
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	

	public List<BusinessAccount> getBusinessAccount() {
		return businessAccount;
	}

	public void setBusinessAccount(List<BusinessAccount> businessAccount) {
		this.businessAccount = businessAccount;
	}

	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public Comment getCopyOfComment() {
		return copyOfComment;
	}

	public void setCopyOfComment(Comment copyOfComment) {
		this.copyOfComment = copyOfComment;
	}

	
}
