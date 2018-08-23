package com.payroll.employee.salary.vo;

import java.io.Serializable;
import java.util.Date;

import com.payroll.Utils;

public class SalaryVO implements Serializable{
	private int employeeId;
	private int year;
	private double basic;
	private double gradePay;
	private String scalePay;
	private String scaleCode;
	private String fullName;
	private int departmentId;
	private int designationId;
	private int headId;
	private double incrementAmount;
	private Date incrementDate; 
	
	public SalaryVO() {
		
	}
	public SalaryVO (int empId, String fName, String lName, int year, double basic, 
			double gradePay, String scalePay, double incrementAmount, Date incrementDate){
		this.employeeId = empId;
		this.year = year;
		this.basic = basic;
		this.gradePay = gradePay;
		//this.scaleInc = scaleInc;
		this.scalePay = scalePay;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.incrementAmount = incrementAmount;
		this.incrementDate = incrementDate;
		
	}
	
	public SalaryVO (int empId, int deptId, int desgId, int headId, int year, double basic, 
			double gradePay, String scalePay, double incrementAmount, Date incrementDate){
		this.employeeId = empId;
		this.departmentId = deptId;
		this.designationId = desgId;
		this.year = year;
		this.basic = basic;
		this.gradePay = gradePay;
		//this.scaleInc = scaleInc;
		this.scalePay = scalePay;
		this.headId = headId;
		this.incrementAmount = incrementAmount;
		this.incrementDate = incrementDate;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public int getYear() {
		return year;
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

	public String getFullName() {
		return fullName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public int getDesignationId() {
		return designationId;
	}
	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}
	public void setYear(int year) {
		this.year = year;
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
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
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
