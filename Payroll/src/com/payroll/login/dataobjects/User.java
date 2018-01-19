package com.payroll.login.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable
{
	private String userId;
	private String password;
	private String confirmPassword;
	private Integer userIdPk;
	private Integer empId;
	private Integer roleId;
	private Integer deptId;
	private String status;
	private Timestamp rowUpdatedDate;
	private boolean userNameAvailabe;
	
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
}
