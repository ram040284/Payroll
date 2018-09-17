package com.payroll.dashboard.dataobjects;

public class PaybillChart {
	
	private String monthDate;
	private Long grossPay;
	private Long deduction;
	private Long netPay;
	
	public PaybillChart() {
		// TODO Auto-generated constructor stub
	}
	
	public PaybillChart(String monthDate, Long grossPay, Long deduction, Long netPay) {
		
		this.monthDate = monthDate;
		this.grossPay = grossPay;
		this.deduction = deduction;
		this.netPay = netPay;
	}
	
	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public Long getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(Long grossPay) {
		this.grossPay = grossPay;
	}

	public Long getDeduction() {
		return deduction;
	}

	public void setDeduction(Long deduction) {
		this.deduction = deduction;
	}

	public Long getNetPay() {
		return netPay;
	}

	public void setNetPay(Long netPay) {
		this.netPay = netPay;
	}

	
}
