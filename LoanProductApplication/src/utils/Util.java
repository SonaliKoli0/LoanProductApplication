package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	// Method to parse date from string
	public static Date parseDate(String dateString) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Method to format date 
	public static String formatDate(Date date) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    // method to convert java.util.date date to java.sql.Date date
	public static java.sql.Date toSQLDate(java.util.Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}
	// method to convert java.sql.Date  date to  java.util.date date
	public static java.util.Date toUtilDate(java.sql.Date date) {
		if (date == null) {
			return null;
		}
		return new java.util.Date(date.getTime());
	}
}
