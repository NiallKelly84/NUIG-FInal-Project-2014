package com.needABuilder.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * 
 * Class to return current date
 * 
 */
public class CurrentDate {
	public String calculateCurrentDateTime() {

		//define the format of the date required
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar cal = Calendar.getInstance();	//get current instance (ie: current date)
		String currentDate = dateFormat.format(cal.getTime());	//get the time with the date and create a string

		return currentDate;	//retrun the date and time

	}

}
