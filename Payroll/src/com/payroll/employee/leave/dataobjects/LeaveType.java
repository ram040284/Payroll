package com.payroll.employee.leave.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

public class LeaveType implements Serializable {
	
	private int id;
	private String name;
	private String description;
	private String status;
	private Timestamp rowUpdDate;
	
	public LeaveType() {
		// TODO Auto-generated constructor stub
	}
	
	public LeaveType(int id, String name, String description, String status, Timestamp rowUpdDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.rowUpdDate = rowUpdDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	

}
