package com.payroll.employee.salary.vo;

import java.io.Serializable;

public class EmployeeSalary implements Serializable{
	@Override
	public String toString() {
		return "EmployeeSalary [employeeId=" + employeeId + ", basic=" + basic + ", gradePay=" + gradePay
				+ ", scalePay=" + scalePay + ", scaleCode=" + scaleCode + "]";
	}
	public EmployeeSalary(int employeeId, double basic, double gradePay, String scalePay, String scaleCode) {
		super();
		this.employeeId = employeeId;
		this.basic = basic;
		this.gradePay = gradePay;
		this.scalePay = scalePay;
		this.scaleCode = scaleCode;
	}
	private int employeeId;
	private double basic;
	private double gradePay;
	private String scalePay;
	private String scaleCode;
	
	public EmployeeSalary() {
		
	}
	public int getEmployeeId() {
		return employeeId;
	}

	public double getBasic() {
		return basic;
	}
	public double getGradePay() {
		return gradePay;
	}
	public String getScalePay() {
		return scalePay;
	}
	public String getScaleCode() {
		return scaleCode;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}
	public void setGradePay(double gradePay) {
		this.gradePay = gradePay;
	}
	public void setScalePay(String scalePay) {
		this.scalePay = scalePay;
	}
	public void setScaleCode(String scaleCode) {
		this.scaleCode = scaleCode;
	}
}
