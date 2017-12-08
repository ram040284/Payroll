package com.payroll.headInfo.vo;

public class HeadInfoVO {
	
	private int headId;
	private String headName;
	private String description;
	private int departmentId;
	private String departmentName;
	
	public HeadInfoVO(int headId, String departmentName, String headName, String description){
		this.headId = headId;
		this.headName = headName;
		this.departmentName = departmentName;
		this.description = description;
	}
	
	public HeadInfoVO(int headId, String departmentName, String headName){
		this.headId = headId;
		this.headName = headName;
		this.departmentName = departmentName;
	}
	
	public int getHeadId() {
		return headId;
	}
	public String getHeadName() {
		return headName;
	}
	public String getDescription() {
		return description;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}

	
}
