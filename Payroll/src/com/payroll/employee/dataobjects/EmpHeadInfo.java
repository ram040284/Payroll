package com.payroll.employee.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.headInfo.dataobjects.HeadInfo;

public class EmpHeadInfo implements Serializable{
	
	private int employeeId;
	private int headId;
	private Date startDate;
	private Date lastWokingDate;
	private String status;
	private Timestamp rowUpdDate;
	private Employee employee;
	private HeadInfo headInfo;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
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
	public HeadInfo getHeadInfo() {
		return headInfo;
	}
	public void setHeadInfo(HeadInfo headInfo) {
		this.headInfo = headInfo;
	}

}
