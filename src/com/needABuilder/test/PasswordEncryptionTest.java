package com.needABuilder.test;
import static org.junit.Assert.*;
import org.junit.Test;

import com.needABuilder.services.PasswordEncrypt;


/*
 * 
 * This is a test to check that the encryption method works
 * 
 */
public class PasswordEncryptionTest {

	@Test
	public void test() {
		String password = "abc";	
		PasswordEncrypt p = new PasswordEncrypt();
		// MD5 value for String="abc" (commonly used for testing)
		String abc = "900150983cd24fb0d6963f7d28e17f72";
		String passwordEncrypted = p.EncryptPass(password);	//encrypt the password above.

		//check that the returned value from EncryotPass method equals the test value
		assertEquals(abc, passwordEncrypted);
	}
}
