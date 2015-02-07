package com.needABuilder.test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.managedBeans.SearchBean;

/*
 * 
 * Test of the searchContractors method in SearchBean class
 * 
 */

public class SearchTest {

	private List<BusinessAccount> contractorList = new ArrayList<BusinessAccount>();

	@Test
	public void test() {
		int countyId = 17; // id for Co. Leitrim
		// only one entry in the database for this county at time of test
		SearchBean s = new SearchBean();
		contractorList = s.searchContractors(countyId);	//returns a list of contractors with work locations matching this id

		//for test purposes, only one company was registered as being in Co. Leitrim
		//only one element will be returned in this list
		//get the business name of this account object
		String companyName = contractorList.get(0).getBusinessName();
		
		//check that the expected and actual business names are equal
		assertEquals("Search Test Business Name", companyName);
	}

}
