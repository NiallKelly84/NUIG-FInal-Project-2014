package com.needABuilder.test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.managedBeans.RegisterBean;
import com.needABuilder.services.PasswordEncrypt;


/*
 * 
 * This purpose of this test is to is to test the 'create' method in the
 * RegisterBean class.
 * 
 * NOTE: This test passes but Faces Messages must be disabled in order for it to run
 * 
*/
public class AddAccountTest {

	private static final String PERSISTENCE_UNIT_NAME = "NeedABuilderUnit";
	private static EntityManagerFactory factory;
	private BusinessAccount b = new BusinessAccount();
	private RegisterBean reg = new RegisterBean();

	@Before
	public void setUp() throws Exception {	//sets up the necessary elements for the test

		b.setEmail("testEmailAddress");	//set email for business account object, b
		b.setPassword("testPassword");	//set password for business account object, b

		reg.setBusinessAccount(b);		//set b as the business account
		reg.create("testEmailAddress");	//pass this email address to the create methd
	}

	@Test
	public void test() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		String email = "testEmailAddress";
		Query myQuery = em
				.createQuery("SELECT u FROM BusinessAccount u WHERE u.email=:email");
		myQuery.setParameter("email", email);	//find the businessAccount matching the email address above
		List<BusinessAccount> accounts = myQuery.getResultList();	//return a list of matching accounts
		PasswordEncrypt pw = new PasswordEncrypt();	

		//check that this email equals the email in the first account object in the list
		assertEquals("testEmailAddress", accounts.get(0).getEmail());
		
		//encrypt the password and check that it matches the password in the account object
		assertEquals(pw.EncryptPass("testPassword"), accounts.get(0)
				.getPassword());
	}

}
