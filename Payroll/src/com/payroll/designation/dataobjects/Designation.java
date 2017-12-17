package com.payroll.designation.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.collections.map.LRUMap;

import com.payroll.department.dataobjects.Department;
import com.payroll.headInfo.dataobjects.HeadInfo;

public class Designation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int designationId;
	private int headId;
	private String description;
	private String designationName;
	private String status;
	private Timestamp rowUpdDate;
	private Department department;
	private HeadInfo headInfo;
	private int departmentId;
	
	
	
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "desgName:"+this.designationName+", desgId:"+this.designationId;
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
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public HeadInfo getHeadInfo() {
		return headInfo;
	}
	public void setHeadInfo(HeadInfo headInfo) {
		this.headInfo = headInfo;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
}
