package com.payroll.employee.leave.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class LeaveRequest implements Serializable {

	private String employeeId;
	private int departmentId;
	private int headId;
	private int leaveReqId;
//	private String leaveType;
	private int noOfLeaves;
	private int leaveBalance;
	private String fromDate;
	private String toDate;
	private String reason;
	private int listDeptId;
	private int listHeadId;
	private String listName;
	private String firstName;
	private Leave leave;
	private String status;
	private Timestamp rowUpdDate;
	private Employee employee;
	private LeaveType leaveType;
	private String leaveTypes;
	private short addUpdate;
	private int designationId;
	private String description;
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getLeaveReqId() {
		return leaveReqId;
	}

	public void setLeaveReqId(int leaveId) {
		this.leaveReqId = leaveId;
	}

//	public String getLeaveType() {
//		return leaveType;
//	}
//
//	public void setLeaveType(String leaveType) {
//		this.leaveType = leaveType;
//	}

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveTypes() {
		return leaveTypes;
	}

	public void setLeaveTypes(String leaveTypes) {
		this.leaveTypes = leaveTypes;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
