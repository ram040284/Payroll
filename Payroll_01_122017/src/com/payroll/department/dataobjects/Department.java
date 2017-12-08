package com.payroll.department.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.payroll.headInfo.dataobjects.HeadInfo;

public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int departmentId;
	private String departmantName;
	private String status;
	private Timestamp rowUpdDate;
	private Set<HeadInfo> costHeads = new HashSet<HeadInfo>();
	
	public Department(){
		
	}
	
	public Department(int departmentId){
		this.departmentId = departmentId;
	}
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmantName() {
		return departmantName;
	}
	public void setDepartmantName(String departmantName) {
		this.departmantName = departmantName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "dName:"+this.departmantName+", dId:"+this.departmentId;
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
	public Set<HeadInfo> getCostHeads() {
		return costHeads;
	}
	public void setCostHeads(Set<HeadInfo> costHeads) {
		this.costHeads = costHeads;
	}
	
}
