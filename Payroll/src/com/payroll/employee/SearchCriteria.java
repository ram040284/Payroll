package com.payroll.employee;

public class SearchCriteria {
	
	private int departmentId;
	private int headId;
	private String firstName;
	private int listDeptId;
	private int listHeadId;
	private String listName;
	private String employeeId;
	private int employeeType;
	
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public int getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}

	
}
