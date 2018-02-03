package com.payroll.hrms.payroll.service;

import java.util.List;

import com.payroll.hrms.payroll.dataobjects.PaybillDetails;

public class HeadWiseReport {
	
	private String headName;
	private int headId;
	private String departmentName;
	private List<PaybillDetails> pbDetailsList;
	
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<PaybillDetails> getPbDetailsList() {
		return pbDetailsList;
	}
	public void setPbDetailsList(List<PaybillDetails> pbDetailsList) {
		this.pbDetailsList = pbDetailsList;
	}

}
