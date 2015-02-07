package com.needABuilder.test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.needABuilder.dao.RegisterDAO;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.Comment;
import com.needABuilder.managedBeans.RegisterBean;
import com.needABuilder.managedBeans.SearchBean;
import com.needABuilder.managedBeans.Util;

/*
 * 
 * Test to confirm the database connection
 * 
 */
public class TestDatabase {

	@Test
	public void test() {
		List<BusinessAccount> b = new ArrayList<BusinessAccount>();
		RegisterDAO reg = new RegisterDAO();
		b = reg.checkIfExists("testEmail");	//check if this email address exists in an account

		//assertion to check expected versus actual email addresses
		assertEquals("testEmail", b.get(0).getEmail());	

	}
}
