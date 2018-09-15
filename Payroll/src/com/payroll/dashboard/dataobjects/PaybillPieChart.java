package com.payroll.dashboard.dataobjects;

public class PaybillPieChart {
	
	private String deptName;
	private String monthDate;
	private Long netPay;
	
	public PaybillPieChart() {
	}
	
	public PaybillPieChart(String deptName, String monthDate, Long netPay) {
		this.deptName = deptName;
		this.monthDate = monthDate;
		this.netPay = netPay;
	}
	
	public String getMonthDate() {
		return monthDate;
	}
	
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getNetPay() {
		return netPay;
	}

	public void setNetPay(Long netPay) {
		this.netPay = netPay;
	}
	
}
