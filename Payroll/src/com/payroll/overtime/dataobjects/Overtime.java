package com.payroll.overtime.dataobjects;

import java.io.Serializable;
import java.util.Date;

public class Overtime implements Serializable{
	
	private int empId;
	private int departmentId;
	private int designationId;
	private Double overtimeAmount;
	private Date overtimeDate;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public Double getOvertimeAmount() {
		return overtimeAmount;
	}
	public void setOvertimeAmount(Double overtimeAmount) {
		this.overtimeAmount = overtimeAmount;
	}
	public Date getOvertimeDate() {
		return overtimeDate;
	}
	public void setOvertimeDate(Date overtimeDate) {
		this.overtimeDate = overtimeDate;
	}
	

}
