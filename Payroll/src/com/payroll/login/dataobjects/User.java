package com.payroll.login.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

import com.payroll.employee.vo.EmployeeVO;

public class User implements Serializable
{
	private String userId;
	private String password;
	private String confirmPassword;
	private Integer userIdPk;
	private Integer empId;
	private Integer roleId;
	private Integer deptId;
	private int listRoleId;
	private int listDeptId;
	private String status;
	private Timestamp rowUpdatedDate;
	private boolean userNameAvailabe;
	private EmployeeVO employee;
	private UserRoles role;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getRowUpdatedDate() {
		return rowUpdatedDate;
	}
	public void setRowUpdatedDate(Timestamp rowUpdatedDate) {
		this.rowUpdatedDate = rowUpdatedDate;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public boolean isUserNameAvailabe() {
		return userNameAvailabe;
	}
	public void setUserNameAvailabe(boolean userNameAvailabe) {
		this.userNameAvailabe = userNameAvailabe;
	}
	public Integer getUserIdPk() {
		return userIdPk;
	}
	public void setUserIdPk(Integer userIdPk) {
		this.userIdPk = userIdPk;
	}
	public EmployeeVO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
	}
	public UserRoles getRole() {
		return role;
	}
	public void setRole(UserRoles role) {
		this.role = role;
	}
	public int getListRoleId() {
		return listRoleId;
	}
	public void setListRoleId(int listRoleId) {
		this.listRoleId = listRoleId;
	}
	public int getListDeptId() {
		return listDeptId;
	}
	public void setListDeptId(int listDeptId) {
		this.listDeptId = listDeptId;
	}
}
