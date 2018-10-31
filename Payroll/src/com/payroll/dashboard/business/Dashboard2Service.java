package com.payroll.dashboard.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.payroll.dashboard.dataobjects.Dashboard2DAO;
import com.payroll.dashboard.dataobjects.MonthlySummary;
import com.payroll.dashboard.dataobjects.PaybillChart;
import com.payroll.dashboard.dataobjects.PaybillPieChart;

public class Dashboard2Service {
	
	public List<PaybillChart> getPaybillChart() {
		
		String currentMonthDate = getCurrentMonthDate();
		String previousMonthDate = getPreviousMonthDate();
		
		return new Dashboard2DAO().getPaybillChart(currentMonthDate, previousMonthDate);
	}
	
	public List<PaybillPieChart> getPaybillPieChart() {
		
		String currentMonthDate = getPreviousMonthDate();
		
		return new Dashboard2DAO().getPaybillPieChart(currentMonthDate);
	}

	public List<MonthlySummary> getMonthlySummary() {
		
		String currentMonthDate = getCurrentMonthDate();
		
		return new Dashboard2DAO().getMonthlySummary(currentMonthDate);
	}
	
	private String getCurrentMonthDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		return dateFormat.format(cal.getTime());
	}
	
	private String getPreviousMonthDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, 1);
		return dateFormat.format(cal.getTime());
	}
	
}
