package com.objectiva.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static final String TIME_FORMAT = "yyyy-MM-dd";
	
	private DateUtils() {
		// prevent construction
	}
	
	public static int getBirthdayMonth(String strTime) {
		SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
		Date date = null;
		try {
			date = formatter.parse(strTime);
		} catch (ParseException e) {
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}
	
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}
	
	
}
