package com.payroll.paybill.vo;

public class PaybillVO {
	private int departmentId;
	private String monthDate;
	private int employeeId;
	private int billType;
	
	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public PaybillVO(){
		
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "departmentId:"+departmentId+" | monthDate:"+this.monthDate;
	}
}
