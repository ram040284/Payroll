package com.payroll.dashboard.dataobjects;

public class MonthlySummary {
	
	private String department;
	private String month;
	private int noOfEmployees;
	private Long totalGrossPay;
	private Long totalDeductions;
	private Long netPay;
	
	public MonthlySummary() {
		// TODO Auto-generated constructor stub
	}
	
	public MonthlySummary(String department, String month, int noOfEmployees, Long totalGrossPay, Long totalDeductions, Long netPay) {
		this.department = department;
		this.month = month;
		this.noOfEmployees = noOfEmployees;
		this.totalGrossPay = totalGrossPay;
		this.totalDeductions = totalDeductions;
		this.netPay = netPay;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(int noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public Long getTotalGrossPay() {
		return totalGrossPay;
	}

	public void setTotalGrossPay(Long totalGrossPay) {
		this.totalGrossPay = totalGrossPay;
	}

	public Long getTotalDeductions() {
		return totalDeductions;
	}

	public void setTotalDeductions(Long totalDeductions) {
		this.totalDeductions = totalDeductions;
	}

	public Long getNetPay() {
		return netPay;
	}

	public void setNetPay(Long netPay) {
		this.netPay = netPay;
	}
	
}
