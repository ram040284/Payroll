package com.payroll.login.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserRoles implements Serializable
{
	private Integer roleId;
	private String roleName;
	private String roleDesc;
	
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "roleName:"+this.roleName+", roleId:"+this.roleId+",roleDesc:" +roleDesc;
	}
}
