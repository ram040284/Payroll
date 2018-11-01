package com.payroll.employee.leave.dataobjects;

import java.sql.Timestamp;
import java.util.Date;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class LeaveRequestVO {
	
	private String employeeId;
	private int noOfLeaves;
	private int leaveBalance;
	private String fromDate;
	private String toDate;
	private String reason;
	private String listName;
	private String firstName;
	private Leave leave;
	private String status;
	private Timestamp rowUpdDate;
	private Employee employee;
	private LeaveType leaveType;
	private String fullName;
	private String description;
	
	public LeaveRequestVO(String employeeId, int noOfLeaves, String fromDate, String toDate, String reason, String listName,
			String firstName, String description) {
		super();
		this.employeeId = employeeId;
		this.noOfLeaves = noOfLeaves;
		this.leaveBalance = leaveBalance;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reason = reason;
		this.listName = listName;
		this.firstName = firstName;
		StringBuffer fullNameSB = new StringBuffer(firstName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(listName));
		this.fullName = fullNameSB.toString();
		this.description = description;
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public Leave getLeave() {
		return leave;
	}
	public void setLeave(Leave leave) {
		this.leave = leave;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
