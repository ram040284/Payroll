package com.payroll.employee.pension.vo;

import java.io.Serializable;
import java.util.Date;

public class EmployeePension implements Serializable{
	@Override
	public String toString() {
		return "EmployeeSalary [employeeId=" + employeeId + ", basic=" + basic + ", gradePay=" + gradePay
				+ ", scalePay=" + scalePay + ", scaleCode=" + scaleCode + "]";
	}
	public EmployeePension(String employeeId, double basic, double gradePay, String scalePay, String scaleCode, double incrementAmount, Date incrementDate) {
		super();
		this.employeeId = employeeId;
		this.basic = basic;
		this.gradePay = gradePay;
		this.scalePay = scalePay;
		this.scaleCode = scaleCode;
		this.incrementAmount = incrementAmount;
		this.incrementDate = incrementDate;
	}
	private String employeeId;
	private double basic;
	private double gradePay;
	private String scalePay;
	private String scaleCode;
	private double incrementAmount;
	private Date incrementDate;
	
	public EmployeePension() {
		
	}
	public String getEmployeeId() {
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
	public double getIncrementAmount() {
		return incrementAmount;
	}
	public void setIncrementAmount(double incrementAmount) {
		this.incrementAmount = incrementAmount;
	}
	public Date getIncrementDate() {
		return incrementDate;
	}
	public void setIncrementDate(Date incrementDate) {
		this.incrementDate = incrementDate;
	}
}
