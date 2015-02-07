package com.needABuilder.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * 
 * Class to encrypt passwords
 * 
 */
public class PasswordEncrypt {
	
	public String EncryptPass(String password){//pass the password as an ergument to this method
	MessageDigest md=null;
	try {
		md = MessageDigest.getInstance("MD5");	//use md5 encryption algorithm
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
    md.update(password.getBytes());	//encodes the password string into a sequence of bytes

    byte byteData[] = md.digest();	//create an array of the byte data

    //iterate through the byte array and convert the byte to hex format
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++) {
     sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));	
    }
    
	return sb.toString();	//return the encrypted password as a string

}
}
