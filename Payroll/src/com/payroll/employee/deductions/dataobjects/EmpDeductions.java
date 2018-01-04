package com.payroll.employee.deductions.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

import com.payroll.employee.dataobjects.Employee;

public class EmpDeductions implements Serializable{
	private int employeeId;
	private double section80C;
	private double cess;
	private double homeLoanIntrst88EE;
	private double selfDisable80U;
	private String status;
	private short addUpdate; // 0 - Add / 1 - update
	private Timestamp rowUpdDate;
	private Employee employee;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public double getSection80C() {
		return section80C;
	}
	public void setSection80C(double section80c) {
		section80C = section80c;
	}
	public double getCess() {
		return cess;
	}
	public void setCess(double cess) {
		this.cess = cess;
	}
	public double getHomeLoanIntrst88EE() {
		return homeLoanIntrst88EE;
	}
	public void setHomeLoanIntrst88EE(double homeLoanIntrst88EE) {
		this.homeLoanIntrst88EE = homeLoanIntrst88EE;
	}
	public double getSelfDisable80U() {
		return selfDisable80U;
	}
	public void setSelfDisable80U(double selfDisable80U) {
		this.selfDisable80U = selfDisable80U;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public short getAddUpdate() {
		return addUpdate;
	}
	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}
	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}
	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
