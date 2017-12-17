package com.payroll.employee;

public class SearchCriteria {
	
	private int departmentId;
	private int headId;
	private String firstName;
	
	public SearchCriteria(){
		
	}
	public SearchCriteria(int departmentId, int headId){
		this.departmentId = departmentId;
		this.headId = headId;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "departmentId:"+departmentId+" |  headId:"+this.headId;
	}

	
}
