package com.payroll.paybill.vo;

public class PaybillVO {
	private int departmentId;
	private String section;
	private String monthDate;
	private String employeeId;
	private int billType;
	
	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
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
