package com.payroll.employee.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.designation.dataobjects.Designation;

public class EmpDesignation implements Serializable{
	
	private int employeeId;
	private int designationId;
	private Date startDate;
	private Date lastWokingDate;
	private String status;
	private Timestamp rowUpdDate;
	private Employee employee;
	private Designation designation;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getLastWokingDate() {
		return lastWokingDate;
	}
	public void setLastWokingDate(Date lastWokingDate) {
		this.lastWokingDate = lastWokingDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	
}
