package com.payroll.employee.leave.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class Leave implements Serializable{
	
	private int employeeId;
	private int leaveId;
	private String leaveType;
	private int noOfLeaves;
	private int leaveBalance;
	private String status;
	private Timestamp rowUpdDate;
	private Employee employee; 
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public int getNoOfLeaves() {
		return noOfLeaves;
	}
	public void setNoOfLeaves(int noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}
	public int getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "employeeId:"+employeeId+" |  leaveType:"+this.leaveType+" | noOfLeaves:"+this.noOfLeaves +
				" | LeaveId:"+leaveId +" | leaveBalance:"+leaveBalance;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
