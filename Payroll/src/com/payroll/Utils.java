package com.payroll;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {
	public static String DDMMYYYY = "dd/MM/yyyy"; 
	public static String safeTrim(String value){
		return (value !=null) ? value.trim():"";
	}
	public static boolean isEmpty(String value){
		return (safeTrim(value).equals("")) ? true : false;
	}
	
	public static Date getStartDateOfMonth(String date){
		Date fomattedDate = null;
		try{
			Calendar cal = getCalender(getAsDate(date, DDMMYYYY));
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    fomattedDate =  cal.getTime();  
		}catch(Exception e){
			e.printStackTrace();
		}
		return fomattedDate;
	}
	
	public static Date getEndDateOfMonth(String date){
		 Calendar cal = getCalender(getAsDate(date, DDMMYYYY));
		 cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		 Date ending = cal.getTime();
		 return ending;
	}
	private static Calendar getCalender(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		System.out.println("Cal is:"+cal);
		return cal;
	}
	public static Date getAsDate(String date, String format){
		try{
			return (!isEmpty(date) ? new SimpleDateFormat(format).parse(date) : new Date());
		}catch(Exception e){
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static String getAsString(Date date, Format format){
		return (date !=null ? format.format(date) : "");
	}
	public static String getSimpleDate(Date date){
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		return getAsString(date, formatter);
	}
	public static String getMonthYear(Date date){
		Format formatter = new SimpleDateFormat("MMMM, yyyy"); 
	    return getAsString(date, formatter);
	}
	
	public static String getFullYear(Date date){
		return getAsString(date, new SimpleDateFormat("yyyy"));
	}
	
	public static String getLastMonthYear(Date date){
		/*Calendar c = new GregorianCalendar();
		c.add(Calendar.MONTH, -1);*/ 	
		 return getMonthYear(getPrevMonth(date));
	}
	public static Date getPrevMonth(Date date){
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MONTH, -1); 	
		return c.getTime();
	}
	public static String daysInMonth(){
		String daysInMonth = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//calendar.set(Calendar.MONTH, month);
		int numDays = calendar.getActualMaximum(Calendar.DATE);
		System.out.println("days in month:"+numDays);
		daysInMonth += numDays;
		return daysInMonth;
	}
}
