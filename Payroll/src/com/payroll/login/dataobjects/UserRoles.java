package com.payroll.login.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserRoles implements Serializable
{
	/*private Integer roleId;
	private Integer userId;*/
	private Integer userRoleId;
	private Roles role;
	private User user;
	private String status;
	private Timestamp rowUpdatedDate;
	
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
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
	
}
