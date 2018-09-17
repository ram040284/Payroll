package com.payroll.dashboard.business;

import java.util.List;

import com.payroll.dashboard.dataobjects.Dashboard2DAO;
import com.payroll.dashboard.dataobjects.MonthlySummary;
import com.payroll.dashboard.dataobjects.PaybillChart;
import com.payroll.dashboard.dataobjects.PaybillPieChart;

public class Dashboard2Service {
	
	public List<PaybillChart> getPaybillChart() {
		
		String currentMonthDate = "2018-05-01";
		String previousMonthDate = "2018-04-01";
		
		return new Dashboard2DAO().getPaybillChart(currentMonthDate, previousMonthDate);
	}
	
	public List<PaybillPieChart> getPaybillPieChart() {
		
		String currentMonthDate = "2018-04-01";
		
		return new Dashboard2DAO().getPaybillPieChart(currentMonthDate);
	}

	public List<MonthlySummary> getMonthlySummary() {
		
		String currentMonthDate = "2018-05-01";
		
		return new Dashboard2DAO().getMonthlySummary(currentMonthDate);
	}
	
}
