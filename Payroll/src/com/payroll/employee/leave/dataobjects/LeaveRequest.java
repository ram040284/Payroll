package com.payroll.employee.leave.dataobjects;

import java.sql.Timestamp;
import java.util.Date;

public class LeaveRequest {

	private int employeeId;
	private int departmentId;
	private int headId;
	private int leaveId;
	private String leaveType;
	private int noOfLeaves;
	private int leaveBalance;
	private Date fromDate;
	private Date toDate;
	private String reason;
	private int listDeptId;
	private int listHeadId;
	private String listName;
	private String firstName;
	private Leave leave;
	private String status;
	private Timestamp rowUpdDate;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getListDeptId() {
		return listDeptId;
	}

	public void setListDeptId(int listDeptId) {
		this.listDeptId = listDeptId;
	}

	public int getListHeadId() {
		return listHeadId;
	}

	public void setListHeadId(int listHeadId) {
		this.listHeadId = listHeadId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
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
		return "LeaveRequest [employeeId=" + employeeId + ", departmentId=" + departmentId + ", headId=" + headId
				+ ", leaveId=" + leaveId + ", leaveType=" + leaveType + ", noOfLeaves=" + noOfLeaves + ", leaveBalance="
				+ leaveBalance + ", fromDate=" + fromDate + ", toDate=" + toDate + ", reason=" + reason
				+ ", listDeptId=" + listDeptId + ", listHeadId=" + listHeadId + ", listName=" + listName
				+ ", firstName=" + firstName + ", leave=" + leave + ", status=" + status + ", rowUpdDate=" + rowUpdDate
				+ "]";
	}
}
