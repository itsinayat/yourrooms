package com.inayat.yourrooms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	/**
	 * 
	 * @param sDate1   SimpleDateFormat("dd/MM/yyyy")
	 * @return
	 * @throws ParseException
	 */
		public static Date getDate(String sDate1) throws ParseException {
		//String sDate1="01/05/2020";
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		return date1;
		}
		
		public static Date addDays(Date d, int days)
	    {
	        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
	        return d;
	    }
}
