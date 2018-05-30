package com.payroll.advance.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


	public double getAdvanceInstallment(int employeeId){
		double festInstallmentAmount = 0;
		EmployeeAdvance employeeAdvance = new AdvanceDAO().getAdvanceByEmployeeId(employeeId);
		List<EmployeeAdvanceDetails> listEmployeeAdvanceDetails = new AdvanceDAO().getEmployeeAdvanceDetails(employeeAdvance.getAdvanceId());
		return festInstallmentAmount;
		
	}
	
	private double calculateInstallment(EmployeeAdvance employeeAdvance, List<EmployeeAdvanceDetails> listemployeeAdvanceDetails){

		Date sDate = employeeAdvance.getAdvanceDate();
		Date todaysDate = new Date();
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(sDate);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(todaysDate);
		
		int difInMonths = endDate.MONTH -  startDate.MONTH;
		
		return 0.0;
	}

}
